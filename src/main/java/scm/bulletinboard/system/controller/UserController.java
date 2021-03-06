package scm.bulletinboard.system.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.bulletinboard.system.common.CommonConstant;
import scm.bulletinboard.system.common.StringUtil;
import scm.bulletinboard.system.form.user.UserCreateForm;
import scm.bulletinboard.system.form.user.UserForm;
import scm.bulletinboard.system.model.User;
import scm.bulletinboard.system.service.UserService;

/**
 * Controller for Login
 */
@Controller
public class UserController {

    /**
     * <h2>User Service</h2>
     * <p>
     * Declare User Service For Using Service Methods
     * </p>
     */
    @Autowired
    UserService userService;

    /**
     * <h2>User Service Error Message</h2>
     * <p>
     * Declare MessageSource For Accessing Messages From Property Resource File
     * </p>
     */
    @Autowired
    MessageSource messageSource;

    /**
     * <h2>Get User List</h2>
     * <p>
     * Go To userlist Route, Show User List Page
     * </p>
     */
    @RequestMapping(value = "userlist", method = RequestMethod.GET)
    public ModelAndView showUsers(ModelAndView model, HttpSession session, HttpServletRequest request) {
        UserForm userForm = new UserForm();
        List<User> userList;
        int userCount;
        if (request.getParameter("page") != null) {
            int pageId = Integer.parseInt(request.getParameter("page"));
            int total = 7;
            if (pageId != 1) {
                pageId = (pageId - 1) * total + 1;
            }
            userList = userService.getUserByPageId(pageId, total);
        } else {
            userList = userService.getAllUsers();
        }
        userCount = userService.getUserCount();
        int paginationCount = userCount / 7;
        if (paginationCount != 0 && (userCount % 7) != 0) {
            ++paginationCount;
        }
        model.addObject("userLists", userList);
        model.addObject("userSearch", userForm);
        model.addObject("paginationCount", paginationCount);
        model.addObject("userCount", userCount);
        model.setViewName("userlist");
        return model;
    }

    /**
     * <h2>Calling User List Route By Search Keys</h2>
     * <p>
     * Go To userlist Route, Show Users List Page By Search Keyss
     * </p>
     */
    @RequestMapping(value = "userlist/searchUsers", method = { RequestMethod.POST })
    public ModelAndView searchUsers(@ModelAttribute("uerSearch") UserForm userForm, HttpSession session)
            throws ParseException {
        ModelAndView searchUserView = new ModelAndView("userlist");
        doSearchUserProcess(searchUserView, CommonConstant.INITIAL_OFFSET, true, userForm);

        if (StringUtil.isEmpty(userForm.getName()) && StringUtil.isEmpty(userForm.getEmail())
                && StringUtil.isEmpty(userForm.getCreatedFrom()) && StringUtil.isEmpty(userForm.getCreatedTo())) {
            return new ModelAndView("redirect:/userlist");
        } else {
            return searchUserView;
        }
    }

    /**
     * <h2>Create User</h2>
     * <p>
     * Go To createuser Route, Show User Creation Form Page
     * </p>
     */
    @RequestMapping(value = "userlist/createuser", method = RequestMethod.GET)
    public ModelAndView createuser(ModelAndView model, HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
        UserCreateForm userCreateForm = new UserCreateForm();
        model.addObject("errorMsg", request.getParameter("errorMsg"));
        model.addObject("user", userCreateForm);
        model.setViewName("createuser");
        return model;
    }

    /**
     * <h2>Go To User Create Confirmation</h2>
     * <p>
     * Go To confirmuser Route And Show Confirm User Page
     * </p>
     */
    @RequestMapping(value = "/userlist/confirmuser", method = RequestMethod.POST)
    public ModelAndView saveUser(@Validated @ModelAttribute(value = "user") UserCreateForm userCreateForm,
            BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
            @RequestParam("imageData") String imageData) throws ParseException {

        if (result.hasErrors() || !userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
            ModelAndView model = new ModelAndView("createuser");
            if (!userCreateForm.getPassword().equals(userCreateForm.getConfirmPassword())) {
                model.addObject("passwordMismatchError", messageSource.getMessage(CommonConstant.MSG_0005, null, null));
            }
            return model;
        } else {
            User currentUser = (User) request.getSession().getAttribute(CommonConstant.LOGIN_USER);
            Date date = userService.getDateData();
            User user = userService.addNewUser(userCreateForm, currentUser, date);
            user.setProfile(imageData);
            ModelAndView model = new ModelAndView("/confirmuser");
            model.addObject("user", user);
            model.addObject("profile", userCreateForm.getProfile());
            return model;
        }
    }

    /**
     * <h2>Confirm User</h2>
     * <p>
     * Go To confirmuser Route and Create Users
     * </p>
     */
    @RequestMapping(value = "/userlist/confirmuser", method = RequestMethod.GET)
    public ModelAndView confirmUser(@Validated @ModelAttribute(value = "user") User user, HttpServletRequest request,
            HttpSession session) {
        ModelAndView model = new ModelAndView();
        if (StringUtil.isNotEmpty(request.getParameter("errorMsg"))) {
            model.addObject("errorMsg", messageSource.getMessage(CommonConstant.MSG_0002, null, null));
            User existUser = userService.getUserByEmail(request.getParameter("email"));
            model.addObject("user", existUser);
        }
        model.addObject("user", user);
        model.setViewName("confirmuser");
        return model;
    }

    /**
     * <h2>Editing User</h2>
     * <p>
     * Go To editUser Route, Show User Creation Form Page To Edit
     * </p>
     */
    @RequestMapping(value = "userlist/editUser")
    public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(userId);
        UserCreateForm userCreateForm = userService.setDataToUserCreateForm(user);
        ModelAndView model = new ModelAndView();
        model.addObject("userForm", userCreateForm);
        model.addObject("user", user);
        model.setViewName("updateuser");
        return model;
    }

    /**
     * <h2>Save User By Editing Or Creation</h2>
     * <p>
     * Go To insertUser Route And User Save to Database And Go To Post List Page
     * </p>
     */
    @RequestMapping(value = "userlist/insertUser", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute("user") UserCreateForm userCreateForm, HttpSession session,
            HttpServletRequest request) throws ParseException, IOException {
        User user = (User) request.getSession().getAttribute(CommonConstant.LOGIN_USER);
        int loginUserId = user.getId();
        String userProfilePath = request.getServletContext().getRealPath("/") + "resources\\profiles";

        User userForCheck = userService.getUserByEmail(userCreateForm.getEmail());
        ModelAndView model = new ModelAndView();
        if (userForCheck != null) {
            model.addObject("errorMsg", messageSource.getMessage(CommonConstant.MSG_0006, null, null));
            userCreateForm.setPassword("");
            model.addObject("user", userCreateForm);
            model.setViewName("createuser");

        } else {
            userService.insertUser(userCreateForm, loginUserId, userProfilePath);
            UserForm userForm = new UserForm();
            model.addObject("userSearch", userForm);
            model.setViewName("redirect:/userlist");
        }
        return model;
    }

    /**
     * <h2>Confirm Update User</h2>
     * <p>
     * Go To confirmUpdateUser Route and Show Update User Confirmation Page
     * </p>
     */
    @RequestMapping(value = "userlist/confirmUpdateUser", method = RequestMethod.POST)
    public ModelAndView confirmupdate(@ModelAttribute(value = "userForm") UserCreateForm userCreateForm,
            @RequestParam("imageData") String imageData) {
        ModelAndView model = new ModelAndView("confirmupdateuser");
        model.addObject("user", userCreateForm);
        model.addObject("profile", imageData);
        return model;

    }

    /**
     * <h2>Update User</h2>
     * <p>
     * Go To updateUser Route and User Updating
     * </p>
     */
    @RequestMapping(value = "userlist/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUserData(@ModelAttribute("user") UserCreateForm userCreateForm, HttpSession session,
            HttpServletRequest request) throws ParseException, IOException {
        User user = (User) request.getSession().getAttribute(CommonConstant.LOGIN_USER);
        String userProfilePath = request.getServletContext().getRealPath("/") + "resources\\profiles";

        User userForCheck = userService.getUserByEmail(userCreateForm.getEmail());
        ModelAndView model = new ModelAndView();
        if (userForCheck != null && userForCheck.getId() != userCreateForm.getId()) {
            model.addObject("errorMsg", messageSource.getMessage(CommonConstant.MSG_0006, null, null));
            model.addObject("userForm", userCreateForm);
            model.setViewName("redirect:/userlist/editUser?id=" + userCreateForm.getId());

        } else {
            userService.updateUser(userCreateForm, user, userProfilePath);
            UserForm userForm = new UserForm();
            model.addObject("userSearch", userForm);
            model.setViewName("redirect:/userlist");
        }
        return model;
    }

    /**
     * <h2>User Profile</h2>
     * <p>
     * Go To userprofile Route and Show User Profile
     * </p>
     */
    @RequestMapping(value = "/userprofile", method = RequestMethod.GET)
    public ModelAndView showProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(CommonConstant.LOGIN_USER);
        ModelAndView model = new ModelAndView();
        model.addObject("currentUser", user);
        model.setViewName("userprofile");
        return model;
    }

    /**
     * <h2>Delete User</h2>
     * <p>
     * Go To deleteUser Route and Delete User
     */
    @RequestMapping(value = "userlist/deleteUser")
    public ModelAndView softDeleteUser(ModelAndView model, HttpServletRequest request, HttpSession session) {
        User currentUser = (User) request.getSession().getAttribute(CommonConstant.LOGIN_USER);
        int userId = (Integer) currentUser.getId();
        Date deletedDate = userService.getDateData();
        userService.softDelete(Integer.parseInt(request.getParameter("id")), userId, deletedDate);
        model.setViewName("redirect:/userlist");
        return model;
    }

    /**
     * <h2>Process For User Searching</h2>
     * <p>
     * Process For User Searching
     * </p>
     */
    private void doSearchUserProcess(ModelAndView view, int offset, Boolean resultSearch, UserForm userForm)
            throws ParseException {
        String searchName = userForm.getName();
        String searchEmail = userForm.getEmail();
        String searchCreatedFrom = userForm.getCreatedFrom().toString();
        String searchCreatedTo = userForm.getCreatedTo().toString();
        int count = this.userService.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom, searchCreatedTo)
                .size();
        List<User> userList = this.userService.getUsersBySearchkeys(searchName, searchEmail, searchCreatedFrom,
                searchCreatedTo);
        if (resultSearch == false && userList.size() == 0) {
            view.addObject("alertMsg", "There is no search result.");
        }
        view.addObject("userSearch", userForm);
        view.addObject("offset", CommonConstant.INITIAL_OFFSET);
        view.addObject("userLists", userList);
        view.addObject("userCount", count);
    }
}
package Servlet;


import Models.FileInfo;
import Models.User;
import Models.UserProfile;
import Repositories.AccountRepository;
import Repositories.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet("/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final long serialVersionUID = 1L;
    private FileService fileService;
    private AccountRepository accountRepository;

    public void init(ServletConfig config) throws ServletException {
        this.fileService = (FileService) config.getServletContext().getAttribute("filesUploadService");
        this.accountRepository = (AccountRepository) config.getServletContext().getAttribute("accountRep");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            UserProfile userProfile = user.getUserProfile();
            request.setAttribute("userProfile", userProfile);
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
            // Rest of the code...
        } else {
            System.out.println("User is null");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (fileService == null) {
            System.out.println("fileService is null");
        }
        if (accountRepository == null) {
            System.out.println("accountRepository is null");
        }

        User user = (User) request.getSession().getAttribute("user");
        System.out.println("User retrieved from session: " + user);

        String bio = request.getParameter("bio");
        String favFoodCategory = request.getParameter("favFoodCategory");
      //  Part filePart = request.getPart("profilePhoto");
//        String storageFileName = "";
//
//        if (filePart != null) {
//            storageFileName = saveProfilePhoto(filePart, user);
//
//        } else {
//            System.out.println("filePart is null");
//
//        }

        //  String storageFileName = saveProfilePhoto(filePart);


        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null) {
            userProfile = new UserProfile();
            user.setUserProfile(userProfile);
        }

        if (userProfile != null) {
            userProfile.setBio(bio);
            userProfile.setFavFoodCategory(favFoodCategory);

            Part filePart = request.getPart("profilePhoto");
            if (filePart != null && filePart.getSize() > 0) {
                String storageFileName = saveProfilePhoto(filePart, user);
                userProfile.getFileInfo().setStorageFileName(storageFileName);
            } else {
                userProfile.getFileInfo().setStorageFileName(userProfile.getFileInfo().getStorageFileName());
            }
            updateUserInformation(user);
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            System.out.println("userProfile is null");
        }


    }

    private String saveProfilePhoto(Part filePart, User user) throws IOException {
        InputStream fileContent = filePart.getInputStream();
        String originalFileName = filePart.getSubmittedFileName();
        String contentType = filePart.getContentType();
        long size = filePart.getSize();

        UserProfile userProfile = user.getUserProfile();
        if (userProfile == null) {
            userProfile = new UserProfile();
            user.setUserProfile(userProfile);
        }

        FileInfo fileInfo = userProfile.getFileInfo();
        if (fileInfo == null) {
            fileInfo = new FileInfo();
            userProfile.setFileInfo(fileInfo);
        }

        fileInfo.setStorageFileName(fileService.saveFileToStorage(fileContent, originalFileName, contentType, size));

        System.out.println("File saved and user information updated!");

        return originalFileName;
    }


    private void updateUserInformation(User user) {
        if (accountRepository != null) {
            accountRepository.updateProfile(user);
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("account repo is null");
        }
    }


}

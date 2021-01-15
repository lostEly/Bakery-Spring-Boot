package com.test.bakery.service;


import com.test.bakery.model.Role;
import com.test.bakery.model.Userr;
import com.test.bakery.repository.RoleRepository;
import com.test.bakery.repository.UserrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.plugin.util.UserProfile;

import java.io.IOException;
import java.nio.file.FileStore;
import java.util.*;

@Service
public class UserService {

    private final UserrRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
//    private final FileStore fileStore;

    public UserService(UserrRepository userEntityRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userEntityRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
//        this.fileStore = fileStore;
    }

    public Userr saveUser(Userr userr) {
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        userr.setRole(userRole);
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        return userRepository.save(userr);
    }

    public Userr findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Userr findByLoginAndPassword(String login, String password) {
        Userr userr = findByLogin(login);
        if (userr != null) {
            if (passwordEncoder.matches(password, userr.getPassword())) {
                return userr;
            }
        }
        return null;
    }

    public Userr saveModer(Userr userr) {
        Role userRole = roleRepository.findByRoleName("ROLE_MODERATOR");
        userr.setRole(userRole);
        userr.setPassword(passwordEncoder.encode(userr.getPassword()));
        return userRepository.save(userr);
    }



//    public Userr findByModerRole(String login)
//    {
//     Userr userr = findByLogin(login);
//     if(userr != null && userr.getRole().equals("ROLE_MODERATOR"))
//     {
//         return userr;
//     }
//     return null;
//    }

//https://www.youtube.com/watch?v=i-hoSg8iRG0
    // https://github.com/amigoscode/spring-s3-react-file-upload/blob/master/src/main/java/com/amigoscode/awsimageupload/profile/UserProfile.java
//
//        List<Userr> getUserProfiles() {
//            return userRepository.getAllUsers();
//        }
//
//        void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
//            // 1. Check if image is not empty
//            isFileEmpty(file);
//            // 2. If file is an image
//            isImage(file);
//
//            // 3. The user exists in our database
//            UserProfile user = getUserProfileOrThrow(userProfileId);
//
//            // 4. Grab some metadata from file if any
//            Map<String, String> metadata = extractMetadata(file);
//
//            // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
//            String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
//            String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
//
//            try {
//                fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
//                user.setUserProfileImageLink(filename);
//            } catch (IOException e) {
//                throw new IllegalStateException(e);
//            }
//
//        }
//
//        byte[] downloadUserProfileImage(UUID userProfileId) {
//            UserProfile user = getUserProfileOrThrow(userProfileId);
//
//            String path = String.format("%s/%s",
//                    BucketName.PROFILE_IMAGE.getBucketName(),
//                    user.getUserProfileId());
//
//            return user.getUserProfileImageLink()
//                    .map(key -> fileStore.download(path, key))
//                    .orElse(new byte[0]);
//
//        }
//
//        private Map<String, String> extractMetadata(MultipartFile file) {
//            Map<String, String> metadata = new HashMap<>();
//            metadata.put("Content-Type", file.getContentType());
//            metadata.put("Content-Length", String.valueOf(file.getSize()));
//            return metadata;
//        }
//
//        private UserProfile getUserProfileOrThrow(UUID userProfileId) {
//            return userProfileDataAccessService
//                    .getUserProfiles()
//                    .stream()
//                    .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
//        }
//
//        private void isImage(MultipartFile file) {
//            if (!Arrays.asList(
//                    IMAGE_JPEG.getMimeType(),
//                    IMAGE_PNG.getMimeType(),
//                    IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
//                throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
//            }
//        }
//
//        private void isFileEmpty(MultipartFile file) {
//            if (file.isEmpty()) {
//                throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
//            }
//        }
//
//    }


}

package com.harshitbhardwaj.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.harshitbhardwaj.constants.Constants.Common.*;

/**
 * @author Harshit Bhardwaj
 */
public final class FileOrDirectoryCreator {

    private FileOrDirectoryCreator() {
    }

    public static class PageFileCreator {

        private PageFileCreator() {
        }

        public static void createPageClass(String className, String obstacleId) {

            // Define file path
            Path baseDir = Paths.get(PAGES_DIRECTORY);
            Path classFile = baseDir.resolve(className + ".java");

            // Generate class content
            String classContent = String.format(
                    """
                            package com.harshitbhardwaj.pages;
                                \s
                            import com.harshitbhardwaj.support.PageInteractionHelper;
                            import com.harshitbhardwaj.support.TestHelper;
                            import org.openqa.selenium.By;
                                \s
                            import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;

                            public class %s implements TestHelper {
                               \s
                                private final PageInteractionHelper pageInteractionHelper;
                                private final String obstacleId = "%s";
                               \s
                                public %s(PageInteractionHelper pageInteractionHelper) {
                                    this.pageInteractionHelper = pageInteractionHelper;
                                }
                               \s
                                @Override
                                public void navigateToObstacle() {
                                    pageInteractionHelper.navigateTo(BASE_URL + obstacleId);
                                }
                               \s
                                @Override
                                public boolean isTestPassed() {
                                    return pageInteractionHelper.isElementDisplayed(goodJobHeading)
                                            && pageInteractionHelper.isElementDisplayed(problemSolvedHeading);
                                }
                            }""",
                    className, obstacleId, className
            );

            try {
                if (!Files.exists(classFile)) {
                    Files.write(classFile, classContent.getBytes());
                    System.out.println("Class file created successfully: " + classFile.toAbsolutePath());
                } else {
                    System.out.println("Class file already exists: " + classFile.toAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TestFileCreator {

        private TestFileCreator() {
        }

        public static void createTestClass(String testClassName,
                                           String pageClassName, String pageObjectName,
                                           String obstacleId, String testName) {

            // Define file path
            Path baseDir = Paths.get(TESTS_DIRECTORY);
            Path testFile = baseDir.resolve(testClassName + ".java");

            // Generate testClass content
            String testClassContent = String.format(
                    """
                            package com.harshitbhardwaj.tests;
                                \s
                            import com.harshitbhardwaj.base.BaseTest;
                            import com.harshitbhardwaj.pages.%s;
                            import io.qameta.allure.Link;
                            import io.qameta.allure.Step;
                            import org.testng.Assert;
                            import org.testng.annotations.BeforeMethod;
                            import org.testng.annotations.Test;
                            import org.slf4j.Logger;
                            import org.slf4j.LoggerFactory;
                               \s
                            import static com.harshitbhardwaj.constants.Constants.Common.BASE_URL;
                               \s
                            public class %s extends BaseTest {
                               \s
                                private static final Logger logger = LoggerFactory.getLogger(%s.class);
                               \s
                                private %s %s;
                               \s
                                @Override
                                @BeforeMethod
                                public void setup() {
                                    super.setup();
                                    %s = new %s(pageInteractionHelper);
                                    %s.navigateToObstacle();
                                }
                               \s
                                @Test
                                @Link("https://obstaclecourse.tricentis.com/Obstacles/%s")
                                public void %s() {
                                    logger.info("####### %s started #######");
                                    // Perform the steps according to the test
                                    Assert.assertTrue(%s.isTestPassed(),"Success Heading was not shown after performing the actions");
                                    logger.info("####### %s succeeded #######");
                                }
                            }""",
                    pageClassName, testClassName, testClassName, pageClassName, pageObjectName, pageObjectName,
                    pageClassName, pageObjectName,
                    obstacleId, testName, testName, pageObjectName, testName
            );

            try {
                if (!Files.exists(testFile)) {
                    Files.write(testFile, testClassContent.getBytes());
                    System.out.println("Test file created successfully: " + testFile.toAbsolutePath());
                } else {
                    System.out.println("Test file already exists: " + testFile.toAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DownloadsDirectoryCreator {

        private DownloadsDirectoryCreator() {
        }

        public static void createDownloadsDirectory() {
            File downloadDir = new File(DOWNLOADS_DIRECTORY);
            if (!downloadDir.exists()) {
                System.out.println("Downloads directory created: " + DOWNLOADS_DIRECTORY);
                downloadDir.mkdirs();
            } else {
                System.out.println("Downloads directory already exists");
            }
        }

        public static void cleanDownloadDirectory() {
            System.out.println("Cleaning the downloads directory: " + DOWNLOADS_DIRECTORY);
            File downloadsDirectory = new File(DOWNLOADS_DIRECTORY);

            if (downloadsDirectory.exists() && downloadsDirectory.isDirectory()) {
                File[] files = downloadsDirectory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            System.out.println("Deleting file: " + file.getName());
                            file.delete();
                        }
                    }
                }
            }
        }
    }
}
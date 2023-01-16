/*//////////////////////////////////////
//       AML account generator        //
//      Author: VolodyaHoi (At0m)     //
//	   Team: Atomic Threat Team       //
//////////////////////////////////////*/

/* Full code in one program. 
    The code is not optimized because I did not fully understand java, I think more 
  experienced people will be able to optimize and simplify this code. Debug-functions are commented out. */

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Desktop;

import java.util.Date;
import java.util.Properties;

import java.io.*;

import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;

public class AML_ag {

    JFrame mainWindow, warningWindow, passwordInfoWindow, saveWindow, saveSuccefullyWindow, clearTextFileAccept, parserExampleWindow; // Frames

    JMenu mainMenu, settingsMenu, helpMenu, linksMenu, debugMenu; // Menu bar

    JMenuItem generateNickNameOption, generatePasswordOption, generateAllOption, saveInTextFile, debugWarningWindow, 
    helpAboutPassword, linkOpenAuthor, linkOpenTeam, linkOpenProjects, linkOpenBots, 
    linkOpenGithubPages, debugLevelsInfoWindow, debugSaveWindow, debugSaveSuccceffully, debugSaveInTextFile,
    debugReadConfig, clearTextFile, debugClearTextFile, debugAcceptClearTextFile, debugOpenLink, helpParserExample; // Menu Items

    JMenuBar mainMenuBar;

    JButton copyGeneratedNickName, copyGeneratedPassword, saveButton, clearButtonAccept, clearButtonDeny,
    saveSuccefullyButton, saveWarningAccept, saveWarningDeny; // Buttons

    JLabel textNickNameGenerate, textPasswordGenerate, textNickNameValue, textPasswordValue,
    textLevelsDifficulty, textHelpAboutFirstString, textHelpAboutSecondString, textHelpAboutThirdString,
    textWarningFirstString, textWarningSecondString, defaultPasswordLevel, textPasswordInfoHardLVL, textPasswordInfoNormalLVL, 
    textPasswordInfoEazyLVL, textPasswordInfoPS, textSaveSuccefully, textClearTextFile, parserExample; // Texts

    JCheckBox autoSaveInTextFile, autoCopyPassword, saveInTextFileWithData, saveInTextFileWithComment, 
    warningWind, saveInTextFileForParsing; // CheckBoxes

    JComboBox levelsDifficulty; // Combobox

    JTextField saveAddComment; // Text field



    // DEFAULT VALUES

    

    String generateNickNameDefault = "Empty";
    String generatePasswordDefault = "Empty";
    String textPasswordLevelDefault = "hard";

    String nickName = "";
    String password = "";

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

    int mainWindowSizeW = 600, mainWindowSizeH = 300;

    int warningWindowSizeW = 400, warningWindowSizeH = 120; // 400 110

    int passwordInfoWindowSizeW = 600, passwordInfoWindowSizeH = 150;

    int saveWindowSizeW = 500, saveWindowSizeH = 100;

    int saveSucceffulyWindowSizeW = 230, saveSucceffulyWindowSizeH = 100;

    int clearTextFileAcceptW = 500, clearTextFileAcceptH = 100;

    int parserExampleW = 800, parserExampleH = 900;
   
    int sizeMainWindowW = (screenSize.width - mainWindowSizeW) / 2, sizeMainWindowH = (screenSize.height - mainWindowSizeH) / 2;

    int sizeWarningWindowW = (screenSize.width - warningWindowSizeW) / 2, sizeWarningWindowH = (screenSize.height - warningWindowSizeH) / 2;

    int sizePIWW = (screenSize.width - passwordInfoWindowSizeW) / 2, sizePIWH = (screenSize.height - passwordInfoWindowSizeH) / 2;

    int sizeSaveWindowW = (screenSize.width - saveWindowSizeW) / 2, sizeSaveWindowH = (screenSize.height - saveWindowSizeH) / 2;

    int sizeSSWW = (screenSize.width - saveSucceffulyWindowSizeW) / 2, sizeSSWH = (screenSize.height - saveSucceffulyWindowSizeH) / 2;

    int sizeCTFAW = (screenSize.width - clearTextFileAcceptW) / 2, sizeCTFAH = (screenSize.height - clearTextFileAcceptH) / 2;

    int sizePEW = (screenSize.width - parserExampleW) / 2, sizePEH = (screenSize.height - parserExampleH) / 2;

    // CHECKBOXES BOOL

    boolean warningWindowState = true, autoClipBoardPassword = true, saveWithData = true, saveWithComment = true,
    autoSaveITF = true, saveInTextFileForParsingBoolean = true;
    
    AML_ag()
    {

        // DIFFICULTY LEVELS CONFIGURATION
        String defaultLevel;
        String AUTO_SAVE, AUTO_COPY_PASS, SAVE_DATA, SAVE_COM, WARNING_W;
        Boolean AUTO_SAVE_BOOL, AUTO_COPY_PASS_BOOL, SAVE_DATA_BOOL, SAVE_COM_BOOL, WARNING_W_BOOL;
        
        String levels[] = {"load1", "load2", "load3"};

        try
        {
            Properties props = new Properties();
            props.load(new FileInputStream(new File("files/config.ini")));
            defaultLevel = props.getProperty("default_level");

            if ( defaultLevel.equals("hard") ) { 
                levels[0] = "hard";
                levels[1] = "normal";
                levels[2] = "eazy";
            } 
            else if ( defaultLevel.equals("normal") ) { 
                levels[0] = "normal";
                levels[1] = "hard";
                levels[2] = "eazy";
            }
            else if ( defaultLevel.equals("eazy")) { 
                levels[0] = "eazy";
                levels[1] = "hard";
                levels[2] = "normal";
            }
            AUTO_SAVE = props.getProperty("auto_save");
            AUTO_COPY_PASS = props.getProperty("auto_copy");
            SAVE_DATA = props.getProperty("save_with_data");
            SAVE_COM = props.getProperty("save_with_comment");
            WARNING_W = props.getProperty("warning_window");
            
            AUTO_SAVE_BOOL = Boolean.parseBoolean(AUTO_SAVE);
            AUTO_COPY_PASS_BOOL = Boolean.parseBoolean(AUTO_COPY_PASS);
            SAVE_DATA_BOOL = Boolean.parseBoolean(SAVE_DATA);
            SAVE_COM_BOOL = Boolean.parseBoolean(SAVE_COM);
            WARNING_W_BOOL = Boolean.parseBoolean(WARNING_W);

        }
        catch (Exception f) {
            throw new RuntimeException(f);
        }

        // MAIN FRAME
       
        mainWindow = new JFrame("AML | Account generator 0.1.0");

        // OPTIONS

        mainMenu = new JMenu("Options");
        mainMenuBar = new JMenuBar();
        generateAllOption = new JMenuItem("Generate");
        saveInTextFile = new JMenuItem("Save");
        clearTextFile = new JMenuItem("Clear text file");
        
        mainMenu.add(generateAllOption);
        mainMenu.add(saveInTextFile);
        mainMenu.add(clearTextFile);

        mainMenuBar.add(mainMenu);

        // SETTINGS

        settingsMenu = new JMenu("Settings");

        autoSaveInTextFile = new JCheckBox("Auto save", AUTO_SAVE_BOOL);
        autoSaveInTextFile.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == autoSaveInTextFile) {
                    if (e.getStateChange() == 1) {
                        autoSaveITF = true;
                    } else {
                        autoSaveITF = false;
                    }
                }
            }
        });
        
        settingsMenu.add(autoSaveInTextFile);

        autoCopyPassword = new JCheckBox("Auto copy password", AUTO_COPY_PASS_BOOL);
        autoCopyPassword.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == autoCopyPassword) {
                    if (e.getStateChange() == 1) {
                        autoClipBoardPassword = true;
                    } else {
                        autoClipBoardPassword = false;
                    }
                }
            }
        });
        settingsMenu.add(autoCopyPassword);

        saveInTextFileWithData = new JCheckBox("Save with data", SAVE_DATA_BOOL);
        saveInTextFileWithData.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == saveInTextFileWithData) {
                    if (e.getStateChange() == 1) {
                        saveWithData = true;
                    } else {
                        saveWithData = false;
                    }
                }
            }});
        settingsMenu.add(saveInTextFileWithData);

        saveInTextFileWithComment = new JCheckBox("Save with comment", SAVE_COM_BOOL);
        saveInTextFileWithComment.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == saveInTextFileWithComment) {
                    if (e.getStateChange() == 1) {
                        saveWithComment = true;
                    } else {
                        saveWithComment = false;
                    }
                }
            }});
        settingsMenu.add(saveInTextFileWithComment);

        warningWind = new JCheckBox("Warning window", WARNING_W_BOOL);
        warningWind.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (e.getSource() == warningWind) {
                    if (e.getStateChange() == 1) {
                        warningWindowState = true;
                    } else {
                        warningWindowState = false;
                    }
                }
            }});
        settingsMenu.add(warningWind);

        saveInTextFileForParsing = new JCheckBox("Save for parsing", AUTO_SAVE_BOOL);
        saveInTextFileForParsing.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getSource() == saveInTextFileForParsing) {
                    if (e.getStateChange() == 1) {
                        saveInTextFileForParsingBoolean = true;
                    } else {
                        saveInTextFileForParsingBoolean = false;
                    }
                }
            }
        });

        defaultPasswordLevel = new JLabel("Default password level: " + defaultLevel);
        settingsMenu.add(defaultPasswordLevel);

        mainMenuBar.add(settingsMenu);

        // HELP

        helpMenu = new JMenu("Help");
        textHelpAboutFirstString = new JLabel("This programm generating random password and nickname");
        textHelpAboutSecondString = new JLabel("You can save got info in text file (accounts.txt)");
        textHelpAboutThirdString = new JLabel("You can castomize default settings in config.ini");
        helpAboutPassword = new JMenuItem("You can see password difficulty levels -->");
        helpParserExample = new JMenuItem("Example for parser on python");
        helpMenu.add(textHelpAboutFirstString);
        helpMenu.add(textHelpAboutSecondString);
        helpMenu.add(textHelpAboutThirdString);
        helpMenu.add(helpAboutPassword);
        helpMenu.add(helpParserExample);
        mainMenuBar.add(helpMenu);

        // LINKS

        linksMenu = new JMenu("Links");

        linkOpenAuthor = new JMenuItem("Author");
        linksMenu.add(linkOpenAuthor);

        linkOpenTeam = new JMenuItem("Team");
        linksMenu.add(linkOpenTeam);

        linkOpenProjects = new JMenuItem("Projects");
        linksMenu.add(linkOpenProjects);

        linkOpenGithubPages = new JMenuItem("GitHub pages");
        linksMenu.add(linkOpenGithubPages);

        linkOpenBots = new JMenuItem("Bots");
        linksMenu.add(linkOpenBots);

        mainMenuBar.add(linksMenu);

        // DEBUG 

        /*debugMenu = new JMenu("Debug");

        debugWarningWindow = new JMenuItem("debug warning");
        debugMenu.add(debugWarningWindow);

        debugLevelsInfoWindow = new JMenuItem("debug password info");
        debugMenu.add(debugLevelsInfoWindow);

        debugSaveWindow = new JMenuItem("debug save");
        debugMenu.add(debugSaveWindow);

        debugSaveSuccceffully = new JMenuItem("debug succeffully save");
        debugMenu.add(debugSaveSuccceffully);

        debugSaveInTextFile = new JMenuItem("debug save in text file");
        debugMenu.add(debugSaveInTextFile);

        debugReadConfig = new JMenuItem("debug read config");
        debugMenu.add(debugReadConfig);

        debugClearTextFile = new JMenuItem("debug clear text file");
        debugMenu.add(debugClearTextFile);

        debugAcceptClearTextFile = new JMenuItem("debug accept clear txt");
        debugMenu.add(debugAcceptClearTextFile);

        debugOpenLink = new JMenuItem("debug open link");
        debugMenu.add(debugOpenLink);

        mainMenuBar.add(debugMenu); */



        mainWindow.setJMenuBar(mainMenuBar);

        // MAIN WINDOW

        textNickNameGenerate = new JLabel("Your random nickname:");
        textNickNameGenerate.setBounds(10, 10, 500, 20);
        mainWindow.add(textNickNameGenerate);

        copyGeneratedNickName = new JButton("COPY");
        copyGeneratedNickName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                StringSelection selection = new StringSelection(textNickNameValue.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);  
            }
        });
        copyGeneratedNickName.setBounds(180, 10, 75, 20);
        mainWindow.add(copyGeneratedNickName);

        textNickNameValue = new JLabel(generateNickNameDefault);
        textNickNameValue.setBounds(20, 30, 500, 20);
        mainWindow.add(textNickNameValue);

        textPasswordGenerate = new JLabel("Your random password:");
        textPasswordGenerate.setBounds(10, 50, 500, 20);
        mainWindow.add(textPasswordGenerate);

        copyGeneratedPassword = new JButton("COPY");
        copyGeneratedPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                StringSelection selection = new StringSelection(textPasswordValue.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);  
            }
        });
        copyGeneratedPassword.setBounds(182, 50, 75, 20);
        mainWindow.add(copyGeneratedPassword);

        textPasswordValue = new JLabel(generatePasswordDefault);
        textPasswordValue.setBounds(20, 70, 500, 20);
        mainWindow.add(textPasswordValue);

        textLevelsDifficulty = new JLabel("Difficulty level:");
        textLevelsDifficulty.setBounds(10, 90, 500, 20);
        mainWindow.add(textLevelsDifficulty);


        levelsDifficulty = new JComboBox(levels);
        levelsDifficulty.setBounds(20, 110, 90, 20);
        mainWindow.add(levelsDifficulty);

        // WARNING AND ACTION LISTENER DEBUG
        /* 
        debugWarningWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warningWindow = new JFrame("[!] Warning [!]");

                textWarningFirstString = new JLabel("Warning! You choosen eazy password difficulty level!");
                textWarningFirstString.setBounds(10, 10, 500, 20);
                warningWindow.add(textWarningFirstString);

                textWarningSecondString = new JLabel(" You can see password levels in Help!");
                textWarningSecondString.setBounds(10, 30, 500, 20);
                warningWindow.add(textWarningSecondString);

                saveWarningAccept = new JButton("Accept");
                saveWarningAccept.setBounds(20, 50, 90, 20);
                warningWindow.add(saveWarningAccept);

                saveWarningDeny = new JButton("Deny");
                saveWarningDeny.setBounds(120, 50, 90, 20);
                warningWindow.add(saveWarningDeny);

                warningWindow.setSize(warningWindowSizeW, warningWindowSizeH);
                warningWindow.setBounds(sizeWarningWindowW, sizeWarningWindowH, warningWindowSizeW, warningWindowSizeH);
                warningWindow.setLayout(null);
                warningWindow.setVisible(true);
            }
        });

        debugLevelsInfoWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordInfoWindow = new JFrame("Passwords difficulty levels information");

                textPasswordInfoHardLVL = new JLabel("Hard level - such password can be hacked in 25576 years [recomemnded];"); 
                textPasswordInfoHardLVL.setBounds(10, 10, 600, 20);
                passwordInfoWindow.add(textPasswordInfoHardLVL);

                textPasswordInfoNormalLVL = new JLabel("Normal level - such password can be hacked in 413 years;"); 
                textPasswordInfoNormalLVL.setBounds(10, 30, 500, 20);
                passwordInfoWindow.add(textPasswordInfoNormalLVL);

                textPasswordInfoEazyLVL = new JLabel("Eazy level - such password can be hacked in 2429 days."); 
                textPasswordInfoEazyLVL.setBounds(10, 50, 500, 20);
                passwordInfoWindow.add(textPasswordInfoEazyLVL);

                textPasswordInfoPS = new JLabel("* info from 2ip.com "); 
                textPasswordInfoPS.setBounds(10, 70, 500, 20);
                passwordInfoWindow.add(textPasswordInfoPS);

                passwordInfoWindow.setSize(passwordInfoWindowSizeW, passwordInfoWindowSizeH);
                passwordInfoWindow.setBounds(sizePIWW, sizePIWH, passwordInfoWindowSizeW, passwordInfoWindowSizeH);
                passwordInfoWindow.setLayout(null);
                passwordInfoWindow.setVisible(true);
            }
        });

        debugSaveWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveWindow = new JFrame("Save process..");

                saveAddComment = new JTextField("Your comment");
                saveAddComment.setBounds(10, 10, 400, 20);
                saveWindow.add(saveAddComment);

                saveButton = new JButton("Save");
                saveButton.setBounds(420, 10, 75, 20);
                saveWindow.add(saveButton);

                saveWindow.setSize(saveWindowSizeW, saveWindowSizeH);
                saveWindow.setBounds(sizeSaveWindowW, sizeSaveWindowH, saveWindowSizeW, saveWindowSizeH);
                saveWindow.setLayout(null);
                saveWindow.setVisible(true);
            }
        });

        debugSaveSuccceffully.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSuccefullyWindow = new JFrame("Save process..");

                textSaveSuccefully = new JLabel("Saved succeffuly!");
                textSaveSuccefully.setBounds(10, 10, 500, 20);
                saveSuccefullyWindow.add(textSaveSuccefully);

                saveSuccefullyButton = new JButton("Accept");
                saveSuccefullyButton.setBounds(20, 30, 90, 20);
                saveSuccefullyWindow.add(saveSuccefullyButton);

                saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                saveSuccefullyWindow.setLayout(null);
                saveSuccefullyWindow.setVisible(true);

                saveSuccefullyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveSuccefullyWindow.setVisible(false);
               
                }
        }); 
               
            }
        }); 

        debugSaveInTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String fix_data = "[" + date.toString() + "] P: mypassword N: mynickname //" + saveAddComment.getText() + "\n";
                System.out.println(fix_data);
                try
                {
                    FileWriter writer = new FileWriter("files/accounts.txt", true);
                    writer.write(fix_data);
                    writer.close();
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }

            }
        }); 

        debugReadConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String configValueDebug;
                String autosaving;
                try
                {
                    Properties props = new Properties();
                    props.load(new FileInputStream(new File("files/config.ini")));
                    configValueDebug = props.getProperty("debug_value");
                    autosaving = props.getProperty("auto_save");
                } 
                catch (Exception f) {
                    throw new RuntimeException(f);
                }

                boolean autosaving_bool = Boolean.parseBoolean(autosaving);

                if ( autosaving_bool == true ) {
                        System.out.println("THIS TRUE");
                    }
            }
        }); 

        debugClearTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    FileWriter writer = new FileWriter("files/accounts.txt", false);
                    writer.write("");
                    writer.close();
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        debugAcceptClearTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextFileAccept = new JFrame("Clear process..");

                textClearTextFile = new JLabel("You want delete all accounts from accounts.txt?");
                textClearTextFile.setBounds(10, 10, 500, 20);
                clearTextFileAccept.add(textClearTextFile);

                clearButtonAccept = new JButton("Accept");
                clearButtonAccept.setBounds(20, 30, 90, 20);
                clearTextFileAccept.add(clearButtonAccept);

                clearButtonDeny = new JButton("Deny");
                clearButtonDeny.setBounds(120, 30, 90, 20);
                clearTextFileAccept.add(clearButtonDeny);

                clearTextFileAccept.setSize(clearTextFileAcceptW, clearTextFileAcceptH);
                clearTextFileAccept.setBounds(sizeCTFAW, sizeCTFAH, clearTextFileAcceptW, clearTextFileAcceptH);
                clearTextFileAccept.setLayout(null);
                clearTextFileAccept.setVisible(true);
            }
        }); 

        debugOpenLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://www.google.com"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 
        */

        // NON DEBUG / ACTIVE FUNCTIONS

        generateAllOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    if (levelsDifficulty.getSelectedItem() == "hard") { // HARD LEVEL

                        // PASSWORD

                        String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                        "s", "t", "u", "v", "x", "y", "z"};

                        String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                "S", "T", "U", "V", "X", "Y", "Z"};


                        int fNumberRandom;
                        int sNumberRandom;
                        int tNumberRandom;

                        int[] numberMassive = new int[3];

                        for ( int i = 0; i < 3; i++) 
                        {
                            numberMassive[i] = 0;
                        }

                        int allNumberRandom = 0 + (int) (Math.random() * 3);

                        int firstAnrNumber;

                        String password = ""; 

                        do 
                        {
                            fNumberRandom = 1 + (int) (Math.random() * 9);
                        } while ( fNumberRandom > 9 );

                        numberMassive[allNumberRandom] = fNumberRandom;

                        if ( allNumberRandom == 0 )
                        {
                            do 
                            {
                                sNumberRandom = 10 + (int) (Math.random() * 99);
                            } while (sNumberRandom > 99);

                            numberMassive[1 + (int) (Math.random() * 2)] = sNumberRandom;
                            for ( int i = 0; i < 3; i++) 
                            {
                                if ( numberMassive[i] == 0 ) 
                                {
                                    do 
                                    {
                                        tNumberRandom = 100 + (int) (Math.random() * 999);
                                    } while (tNumberRandom > 999);

                                    numberMassive[i] = tNumberRandom;
                                }
                            }
                        }
                        else if ( allNumberRandom == 1 ) 
                        {
                            do 
                            {
                                firstAnrNumber = 0 + (int) (Math.random() * 2);
                                if ( firstAnrNumber != 1 )
                                {
                                    break;
                                }
                            } while (true);

                            do 
                            {
                                sNumberRandom = 10 + (int) (Math.random() * 99);
                            } while (sNumberRandom > 99);

                            numberMassive[firstAnrNumber] = sNumberRandom;
                            for ( int i = 0; i < 3; i++) 
                            {
                                if ( numberMassive[i] == 0 ) 
                                {
                                    do 
                                    {
                                        tNumberRandom = 100 + (int) (Math.random() * 999);
                                    } while (tNumberRandom > 999);

                                    numberMassive[i] = tNumberRandom;
                                }
                            }
                        }
                        else if ( allNumberRandom == 2 ) 
                        {
                            firstAnrNumber = 0 + (int) (Math.random() * 1);
                            do 
                            {
                                sNumberRandom = 10 + (int) (Math.random() * 99);
                            } while (sNumberRandom > 99);
                            numberMassive[firstAnrNumber] = sNumberRandom;

                            for ( int i = 0; i < 3; i++) 
                            {
                                if ( numberMassive[i] == 0 ) 
                                {
                                    do 
                                    {
                                        tNumberRandom = 100 + (int) (Math.random() * 999);
                                    } while (tNumberRandom > 999);
                                    numberMassive[i] = tNumberRandom;
                                }
                            }
                        }

                        for ( int i = 0; i < 3; i++ ) 
                        {

                            int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                            int alfabetStrongNumber = 0 + (int) (Math.random() * 24);

                            int[] alfabetGotValueMassive = {alfabetLargeNumber, alfabetStrongNumber};

                            int cubeOfYourLife = 0 + (int) (Math.random() * 5);
                            int cubeLargeFriend = 0 + (int) (Math.random() * 1);
                            int cubeStrongFriend = 0 + (int) (Math.random() * 1);

                            if ( cubeOfYourLife == 0 )
                            {
                                password = password + numberMassive[i] + alfabetLargeMassive[alfabetLargeNumber] + alfabetStrongMassive[alfabetStrongNumber];
                            }
                            else if ( cubeOfYourLife == 1 ) 
                            {
                                password = password + numberMassive[i] + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
                            }
                            else if ( cubeOfYourLife == 2 )
                            {
                                password = numberMassive[i] + password + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
                            }
                            else if ( cubeOfYourLife == 3 )
                            {
                                password = alfabetStrongMassive[alfabetStrongNumber] + password + numberMassive[i]+ alfabetLargeMassive[alfabetLargeNumber];
                            }
                            else if ( cubeOfYourLife == 4 )
                            {
                                password = alfabetStrongMassive[alfabetStrongNumber] + numberMassive[i] + alfabetLargeMassive[alfabetLargeNumber] + password;
                            }
                            else if ( cubeOfYourLife == 5 )
                            {
                                password =  alfabetLargeMassive[alfabetLargeNumber] + alfabetStrongMassive[alfabetStrongNumber] + numberMassive[i] + password;
                            }

                        }
                        textPasswordValue.setText(password);

                    } else if (levelsDifficulty.getSelectedItem() == "normal") { // NORMAL LEVEL
                        
                        // PASSWORD

                        String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                        "s", "t", "u", "v", "x", "y", "z"};

                        String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                "S", "T", "U", "V", "X", "Y", "Z"};

                        String password = "";

                        for (int i = 0; i < 2; i++){
                            int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                            int alfabetStrongNumber = 0 + (int) (Math.random() * 24);
                            int doubleNumber;
                            do 
                            {
                                doubleNumber = 10 + (int) (Math.random() * 99);
                            } while (doubleNumber > 99);

                            password = password + alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber] + doubleNumber;
                        }

                        int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                        int alfabetStrongNumber = 0 + (int) (Math.random() * 24);

                        password += alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];

                        alfabetStrongNumber = 0 + (int) (Math.random() * 24);
                        password += alfabetStrongMassive[alfabetStrongNumber];

                        textPasswordValue.setText(password);

                    } else if (levelsDifficulty.getSelectedItem() == "eazy") { // EAZY LEVEL

                        // WARNING

                        if (warningWindowState == true) {

                            warningWindow = new JFrame("[!] Warning [!]");

                            textWarningFirstString = new JLabel("Warning! You choosen eazy password difficulty level!");
                            textWarningFirstString.setBounds(10, 10, 500, 20);
                            warningWindow.add(textWarningFirstString);

                            textWarningSecondString = new JLabel(" You can see password levels in Help!");
                            textWarningSecondString.setBounds(10, 30, 500, 20);
                            warningWindow.add(textWarningSecondString);

                            saveWarningAccept = new JButton("Accept");
                            saveWarningAccept.setBounds(20, 50, 90, 20);
                            warningWindow.add(saveWarningAccept);

                            saveWarningDeny = new JButton("Deny");
                            saveWarningDeny.setBounds(120, 50, 90, 20);
                            warningWindow.add(saveWarningDeny);

                            warningWindow.setSize(warningWindowSizeW, warningWindowSizeH);
                            warningWindow.setBounds(sizeWarningWindowW, sizeWarningWindowH, warningWindowSizeW, warningWindowSizeH);
                            warningWindow.setLayout(null);
                            warningWindow.setVisible(true);

                            saveWarningAccept.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // PASSWORD

                                    String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                                    "s", "t", "u", "v", "x", "y", "z"};

                                    String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                            "S", "T", "U", "V", "X", "Y", "Z"};

                                    String password = "";

                                    int threeNumber;

                                    for (int i = 0; i < 3; i++) {
                                        int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                                        int alfabetStrongNumber = 0 + (int) (Math.random() * 24);
                                        password += alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
                                    }
                                    do 
                                    {
                                        threeNumber = 100 + (int) (Math.random() * 999);
                                    } while (threeNumber > 999);

                                    password += threeNumber;

                                    int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                                    password += alfabetLargeMassive[alfabetLargeNumber];

                                    textPasswordValue.setText(password);

                                    if (autoClipBoardPassword == true) {
                                        StringSelection selection = new StringSelection(textPasswordValue.getText());
                                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                                        clipboard.setContents(selection, selection);  
                                    }
                                   
                                    warningWindow.setVisible(false);                          
                                }
                            });

                            saveWarningDeny.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    warningWindow.setVisible(false);

                                }
                            });

                        } else if (warningWindowState == false) {
                            // PASSWORD

                            String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                            "s", "t", "u", "v", "x", "y", "z"};

                            String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                    "S", "T", "U", "V", "X", "Y", "Z"};

                            String password = "";

                            int threeNumber;

                            for (int i = 0; i < 3; i++) {
                                int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                                int alfabetStrongNumber = 0 + (int) (Math.random() * 24);
                                password += alfabetStrongMassive[alfabetStrongNumber] + alfabetLargeMassive[alfabetLargeNumber];
                            }
                            do 
                            {
                                threeNumber = 100 + (int) (Math.random() * 999);
                            } while (threeNumber > 999);

                            password += threeNumber;

                            int alfabetLargeNumber = 0 + (int) (Math.random() * 24);
                            password += alfabetLargeMassive[alfabetLargeNumber];

                            textPasswordValue.setText(password);

                        }                                        
                    }

                    // NICKNAME

                    String[] alfabetLargeMassive = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                    "s", "t", "u", "v", "x", "y", "z"};

                    String[] alfabetStrongMassive = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                            "S", "T", "U", "V", "X", "Y", "Z"};

                    String nickName = "";
                            
                    int nickTagNumber;
                    do
                    {
                        nickTagNumber = 1000 + (int) (Math.random() * 9999);
                        
                    } while (nickTagNumber > 9999);

                    int nickNameRandom;

                    for ( int i = 0; i < 3; i++) 
                    {
                        nickNameRandom = 0 + (int) (Math.random() * 5);

                        if (nickNameRandom == 0) 
                        {
                            nickName = nickName + alfabetLargeMassive[0 + (int) (Math.random() * 24)] + alfabetStrongMassive[0 + (int) (Math.random() * 24)];       
                        }
                        else if (nickNameRandom == 1) 
                        {
                            nickName = nickName + alfabetStrongMassive[0 + (int) (Math.random() * 24)] + alfabetLargeMassive[0 + (int) (Math.random() * 24)];                               
                        }
                        else if (nickNameRandom == 2)
                        {
                            nickName = alfabetLargeMassive[0 + (int) (Math.random() * 24)] + nickName + alfabetStrongMassive[0 + (int) (Math.random() * 24)];
                        }
                        else if (nickNameRandom == 3)
                        {
                            nickName = alfabetLargeMassive[0 + (int) (Math.random() * 24)] + alfabetStrongMassive[0 + (int) (Math.random() * 24)]  + nickName;                  
                        }
                        else if (nickNameRandom == 4)
                        {
                            nickName = alfabetStrongMassive[0 + (int) (Math.random() * 24)] + alfabetLargeMassive[0 + (int) (Math.random() * 24)] + nickName;
                        }
                        else if (nickNameRandom == 5)
                        {
                            nickName = alfabetStrongMassive[0 + (int) (Math.random() * 24)] + nickName + alfabetLargeMassive[0 + (int) (Math.random() * 24)];
                        }
                    }

                    nickName = nickName + nickTagNumber;

                    textNickNameValue.setText(nickName);

                    if (autoClipBoardPassword == true) {
                        StringSelection selection = new StringSelection(textPasswordValue.getText());
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(selection, selection);  
                    }

                    if (autoSaveITF == true) {
                        String fix_data = "Empty";
                        Date date = new Date();
                        if (saveWithData == true && saveWithComment == true) {
                            saveWindow = new JFrame("Save process..");

                            saveAddComment = new JTextField("Your comment");
                            saveAddComment.setBounds(10, 10, 400, 20);
                            saveWindow.add(saveAddComment);

                            saveButton = new JButton("Save");
                            saveButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Date date = new Date();
                                    String fix_data = "[" + date.toString() + "] P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + " //" + saveAddComment.getText() + "\n"; 
                                    try{
                                        FileWriter writer = new FileWriter("files/accounts.txt", true);
                                        writer.write(fix_data);
                                        writer.close();
                                    }
                                    catch (Exception f) {
                                        throw new RuntimeException(f);
                                        }
                                    saveWindow.setVisible(false);
                                    saveSuccefullyWindow = new JFrame("Save process..");

                                    textSaveSuccefully = new JLabel("Saved succeffuly!");
                                    textSaveSuccefully.setBounds(10, 10, 500, 20);
                                    saveSuccefullyWindow.add(textSaveSuccefully);

                                    saveSuccefullyButton = new JButton("Accept");
                                    saveSuccefullyButton.setBounds(20, 30, 90, 20);
                                    saveSuccefullyWindow.add(saveSuccefullyButton);

                                    saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                    saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                    saveSuccefullyWindow.setLayout(null);
                                    saveSuccefullyWindow.setVisible(true);

                                    saveSuccefullyButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        saveSuccefullyWindow.setVisible(false);
                                        }                    
                                    });
                                }

                            });
                            saveButton.setBounds(420, 10, 75, 20);
                            saveWindow.add(saveButton);

                            saveWindow.setSize(saveWindowSizeW, saveWindowSizeH);
                            saveWindow.setBounds(sizeSaveWindowW, sizeSaveWindowH, saveWindowSizeW, saveWindowSizeH);
                            saveWindow.setLayout(null);
                            saveWindow.setVisible(true);

                        } else if (saveWithData == true && saveWithComment == false) {

                            date = new Date();
                            fix_data = "[" + date.toString() + "] P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + "\n";
                            
                            FileWriter writer = new FileWriter("files/accounts.txt", true);
                            writer.write(fix_data);
                            writer.close();
                            
                            saveSuccefullyWindow = new JFrame("Save process..");

                            textSaveSuccefully = new JLabel("Saved succeffuly!");
                            textSaveSuccefully.setBounds(10, 10, 500, 20);
                            saveSuccefullyWindow.add(textSaveSuccefully);

                            saveSuccefullyButton = new JButton("Accept");
                            saveSuccefullyButton.setBounds(20, 30, 90, 20);
                            saveSuccefullyWindow.add(saveSuccefullyButton);

                            saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                            saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                            saveSuccefullyWindow.setLayout(null);
                            saveSuccefullyWindow.setVisible(true);

                            saveSuccefullyButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                saveSuccefullyWindow.setVisible(false);
                                }                    
                            });

                        } else if (saveWithData == false && saveWithComment == true) {

                            saveWindow = new JFrame("Save process..");

                            saveAddComment = new JTextField("Your comment");
                            saveAddComment.setBounds(10, 10, 400, 20);
                            saveWindow.add(saveAddComment);

                            saveButton = new JButton("Save");
                            saveButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String fix_data = "P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + " //" + saveAddComment.getText() + "\n";
                                    try{
                                        FileWriter writer = new FileWriter("files/accounts.txt", true);
                                        writer.write(fix_data);
                                        writer.close();
                                    }
                                    catch (Exception f) {
                                        throw new RuntimeException(f);
                                        }
                                    saveWindow.setVisible(false);

                                    saveSuccefullyWindow = new JFrame("Save process..");

                                    textSaveSuccefully = new JLabel("Saved succeffuly!");
                                    textSaveSuccefully.setBounds(10, 10, 500, 20);
                                    saveSuccefullyWindow.add(textSaveSuccefully);

                                    saveSuccefullyButton = new JButton("Accept");
                                    saveSuccefullyButton.setBounds(20, 30, 90, 20);
                                    saveSuccefullyWindow.add(saveSuccefullyButton);

                                    saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                    saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                    saveSuccefullyWindow.setLayout(null);
                                    saveSuccefullyWindow.setVisible(true);

                                    saveSuccefullyButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        saveSuccefullyWindow.setVisible(false);
                                        }                    
                                    });
                                }

                            });
                            saveButton.setBounds(420, 10, 75, 20);
                            saveWindow.add(saveButton);

                            saveWindow.setSize(saveWindowSizeW, saveWindowSizeH);
                            saveWindow.setBounds(sizeSaveWindowW, sizeSaveWindowH, saveWindowSizeW, saveWindowSizeH);
                            saveWindow.setLayout(null);
                            saveWindow.setVisible(true);
                        
                        } else if (saveWithData == false && saveWithComment == false) {

                            fix_data = "P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + "\n";

                            FileWriter writer = new FileWriter("files/accounts.txt", true);
                            writer.write(fix_data);
                            writer.close();

                            saveSuccefullyWindow = new JFrame("Save process..");

                            textSaveSuccefully = new JLabel("Saved succeffuly!");
                            textSaveSuccefully.setBounds(10, 10, 500, 20);
                            saveSuccefullyWindow.add(textSaveSuccefully);

                            saveSuccefullyButton = new JButton("Accept");
                            saveSuccefullyButton.setBounds(20, 30, 90, 20);
                            saveSuccefullyWindow.add(saveSuccefullyButton);

                            saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                            saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                            saveSuccefullyWindow.setLayout(null);
                            saveSuccefullyWindow.setVisible(true);

                            saveSuccefullyButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                saveSuccefullyWindow.setVisible(false);
                                }                    
                            });
                        }
                    }
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        helpAboutPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    passwordInfoWindow = new JFrame("Passwords difficulty levels information");

                    textPasswordInfoHardLVL = new JLabel("Hard level - such password can be hacked in 25576 years [recomemnded];"); 
                    textPasswordInfoHardLVL.setBounds(10, 10, 600, 20);
                    passwordInfoWindow.add(textPasswordInfoHardLVL);

                    textPasswordInfoNormalLVL = new JLabel("Normal level - such password can be hacked in 413 years;"); 
                    textPasswordInfoNormalLVL.setBounds(10, 30, 500, 20);
                    passwordInfoWindow.add(textPasswordInfoNormalLVL);

                    textPasswordInfoEazyLVL = new JLabel("Eazy level - such password can be hacked in 2429 days."); 
                    textPasswordInfoEazyLVL.setBounds(10, 50, 500, 20);
                    passwordInfoWindow.add(textPasswordInfoEazyLVL);

                    textPasswordInfoPS = new JLabel("* info from 2ip.com "); 
                    textPasswordInfoPS.setBounds(10, 70, 500, 20);
                    passwordInfoWindow.add(textPasswordInfoPS);

                    passwordInfoWindow.setSize(passwordInfoWindowSizeW, passwordInfoWindowSizeH);
                    passwordInfoWindow.setBounds(sizePIWW, sizePIWH, passwordInfoWindowSizeW, passwordInfoWindowSizeH);
                    passwordInfoWindow.setLayout(null);
                    passwordInfoWindow.setVisible(true);
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        linkOpenAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://vk.com/cutie_b0y"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        linkOpenTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("http://vk.com/atomicthreatgroup"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        linkOpenProjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("https://github.com/VolodyaHoi?tab=repositories"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        linkOpenGithubPages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("https://Atomic-Threat-Team.github.io"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        linkOpenBots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Desktop.getDesktop().browse(new URI("https://github.com/VolodyaHoi"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        saveInTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String fix_data = "Empty";
                    Date date = new Date();
                    if (saveWithData == true && saveWithComment == true) {
                        saveWindow = new JFrame("Save process..");

                        saveAddComment = new JTextField("Your comment");
                        saveAddComment.setBounds(10, 10, 400, 20);
                        saveWindow.add(saveAddComment);

                        saveButton = new JButton("Save");
                        saveButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Date date = new Date();
                                String fix_data = "[" + date.toString() + "] P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + " //" + saveAddComment.getText() + "\n"; 
                                try{
                                    FileWriter writer = new FileWriter("files/accounts.txt", true);
                                    writer.write(fix_data);
                                    writer.close();
                                }
                                catch (Exception f) {
                                    throw new RuntimeException(f);
                                    }
                                saveWindow.setVisible(false);

                                saveSuccefullyWindow = new JFrame("Save process..");

                                textSaveSuccefully = new JLabel("Saved succeffuly!");
                                textSaveSuccefully.setBounds(10, 10, 500, 20);
                                saveSuccefullyWindow.add(textSaveSuccefully);

                                saveSuccefullyButton = new JButton("Accept");
                                saveSuccefullyButton.setBounds(20, 30, 90, 20);
                                saveSuccefullyWindow.add(saveSuccefullyButton);

                                saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                saveSuccefullyWindow.setLayout(null);
                                saveSuccefullyWindow.setVisible(true);

                                saveSuccefullyButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    saveSuccefullyWindow.setVisible(false);
                                    }                    
                                });
                            }

                        });
                        saveButton.setBounds(420, 10, 75, 20);
                        saveWindow.add(saveButton);

                        saveWindow.setSize(saveWindowSizeW, saveWindowSizeH);
                        saveWindow.setBounds(sizeSaveWindowW, sizeSaveWindowH, saveWindowSizeW, saveWindowSizeH);
                        saveWindow.setLayout(null);
                        saveWindow.setVisible(true);

                    } else if (saveWithData == true && saveWithComment == false) {

                        date = new Date();
                        fix_data = "[" + date.toString() + "] P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + "\n";
                        
                        FileWriter writer = new FileWriter("files/accounts.txt", true);
                        writer.write(fix_data);
                        writer.close();
                        
                        saveSuccefullyWindow = new JFrame("Save process..");

                        textSaveSuccefully = new JLabel("Saved succeffuly!");
                        textSaveSuccefully.setBounds(10, 10, 500, 20);
                        saveSuccefullyWindow.add(textSaveSuccefully);

                        saveSuccefullyButton = new JButton("Accept");
                        saveSuccefullyButton.setBounds(20, 30, 90, 20);
                        saveSuccefullyWindow.add(saveSuccefullyButton);

                        saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                        saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                        saveSuccefullyWindow.setLayout(null);
                        saveSuccefullyWindow.setVisible(true);

                        saveSuccefullyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            saveSuccefullyWindow.setVisible(false);
                            }                    
                        });

                    } else if (saveWithData == false && saveWithComment == true) {

                        saveWindow = new JFrame("Save process..");

                        saveAddComment = new JTextField("Your comment");
                        saveAddComment.setBounds(10, 10, 400, 20);
                        saveWindow.add(saveAddComment);

                        saveButton = new JButton("Save");
                        saveButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String fix_data = "P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + " //" + saveAddComment.getText() + "\n";
                                try{
                                    FileWriter writer = new FileWriter("files/accounts.txt", true);
                                    writer.write(fix_data);
                                    writer.close();
                                }
                                catch (Exception f) {
                                    throw new RuntimeException(f);
                                    }
                                saveWindow.setVisible(false);

                                saveSuccefullyWindow = new JFrame("Save process..");

                                textSaveSuccefully = new JLabel("Saved succeffuly!");
                                textSaveSuccefully.setBounds(10, 10, 500, 20);
                                saveSuccefullyWindow.add(textSaveSuccefully);

                                saveSuccefullyButton = new JButton("Accept");
                                saveSuccefullyButton.setBounds(20, 30, 90, 20);
                                saveSuccefullyWindow.add(saveSuccefullyButton);

                                saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                                saveSuccefullyWindow.setLayout(null);
                                saveSuccefullyWindow.setVisible(true);

                                saveSuccefullyButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    saveSuccefullyWindow.setVisible(false);
                                    }                    
                                });
                            }

                        });
                        saveButton.setBounds(420, 10, 75, 20);
                        saveWindow.add(saveButton);

                        saveWindow.setSize(saveWindowSizeW, saveWindowSizeH);
                        saveWindow.setBounds(sizeSaveWindowW, sizeSaveWindowH, saveWindowSizeW, saveWindowSizeH);
                        saveWindow.setLayout(null);
                        saveWindow.setVisible(true);
                        
                    
                    } else if (saveWithData == false && saveWithComment == false) {

                        fix_data = "P: " + textPasswordValue.getText() + " N: " + textNickNameValue.getText() + "\n";

                        FileWriter writer = new FileWriter("files/accounts.txt", true);
                        writer.write(fix_data);
                        writer.close();

                        saveSuccefullyWindow = new JFrame("Save process..");

                        textSaveSuccefully = new JLabel("Saved succeffuly!");
                        textSaveSuccefully.setBounds(10, 10, 500, 20);
                        saveSuccefullyWindow.add(textSaveSuccefully);

                        saveSuccefullyButton = new JButton("Accept");
                        saveSuccefullyButton.setBounds(20, 30, 90, 20);
                        saveSuccefullyWindow.add(saveSuccefullyButton);

                        saveSuccefullyWindow.setSize(saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                        saveSuccefullyWindow.setBounds(sizeSSWW, sizeSSWH, saveSucceffulyWindowSizeW, saveSucceffulyWindowSizeH);
                        saveSuccefullyWindow.setLayout(null);
                        saveSuccefullyWindow.setVisible(true);

                        saveSuccefullyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            saveSuccefullyWindow.setVisible(false);
                            }                    
                        });
                    }

                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
            }
        }); 

        clearTextFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    clearTextFileAccept = new JFrame("Clear process..");

                    textClearTextFile = new JLabel("You want delete all accounts from accounts.txt?");
                    textClearTextFile.setBounds(10, 10, 500, 20);
                    clearTextFileAccept.add(textClearTextFile);

                    clearButtonAccept = new JButton("Accept");
                    clearButtonAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try 
                            {
                                FileWriter writer = new FileWriter("files/accounts.txt", false);
                                writer.write("");
                                writer.close();
                            }
                            catch (Exception f) {
                                throw new RuntimeException(f);
                            } 
                            clearTextFileAccept.setVisible(false);
                        }
                    });
                    clearButtonAccept.setBounds(20, 30, 90, 20);
                    clearTextFileAccept.add(clearButtonAccept);

                    clearButtonDeny = new JButton("Deny");
                    clearButtonDeny.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clearTextFileAccept.setVisible(false);
                        }
                    });
                    clearButtonDeny.setBounds(120, 30, 90, 20);
                    clearTextFileAccept.add(clearButtonDeny);

                    clearTextFileAccept.setSize(clearTextFileAcceptW, clearTextFileAcceptH);
                    clearTextFileAccept.setBounds(sizeCTFAW, sizeCTFAH, clearTextFileAcceptW, clearTextFileAcceptH);
                    clearTextFileAccept.setLayout(null);
                    clearTextFileAccept.setVisible(true);
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }            
            }
        }); 

        helpParserExample.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parserExampleWindow = new JFrame("Parser example [Python]");

                parserExample = new JLabel("//code");
                parserExample.setBounds(10, 10, 500, 20);
                parserExampleWindow.add(parserExample);

                parserExampleWindow.setSize(500, 500);
                parserExampleWindow.setBounds(100, 100, 500, 500);
                parserExampleWindow.setLayout(null);
                parserExampleWindow.setVisible(true);
            }
        }); 

        // TOOLTIPS

        textLevelsDifficulty.setToolTipText("You can see levels in Help.");
        generateAllOption.setToolTipText("Generate password and nickname.");
        saveInTextFile.setToolTipText("Save generated info in accounts.txt.");
        autoSaveInTextFile.setToolTipText("Auto save generated info in accounts.txt.");
        saveInTextFileWithData.setToolTipText("Save info with generating data: DD/MM/YYYY.");
        saveInTextFileWithComment.setToolTipText("Save info with comment.\nFor example: // Account for google.");
        defaultPasswordLevel.setToolTipText("Default password level from config.ini.");
        helpAboutPassword.setToolTipText("Click. x)");
        saveInTextFileForParsing.setToolTipText("Save data for parsing.")
        helpParserExample.setToolTipText("Parser example on Python.")



        mainWindow.setSize(mainWindowSizeW, mainWindowSizeH);
        mainWindow.setBounds(sizeMainWindowW, sizeMainWindowH, mainWindowSizeW, mainWindowSizeH);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
    }

    public static void main (String args[]) throws IOException {

        new AML_ag();      

    }
}

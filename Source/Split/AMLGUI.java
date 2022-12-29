/*//////////////////////////////////////
//       AML account generator        //
//      Author: VolodyaHoi (At0m)     //
//	   Team: Atomic Threat Team       //
//////////////////////////////////////*/

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Desktop;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import java.io.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

public class AMLGUI {

    JFrame mainWindow, warningWindow, passwordInfoWindow, saveWindow, saveSuccefullyWindow, clearTextFileAccept; // Frames

    JMenu mainMenu, settingsMenu, helpMenu, linksMenu, debugMenu; // Menu bar

    JMenuItem generateNickNameOption, generatePasswordOption, generateAllOption, saveInTextFile, debugWarningWindow, 
    helpAboutPassword, linkOpenAuthor, linkOpenTeam, linkOpenProjects, linkOpenBots, 
    linkOpenGithubPages, debugLevelsInfoWindow, debugSaveWindow, debugSaveSuccceffully, debugSaveInTextFile,
    debugReadConfig, clearTextFile, debugClearTextFile, debugAcceptClearTextFile, debugOpenLink; // Menu Items

    JMenuBar mainMenuBar;

    JButton copyGeneratedNickName, copyGeneratedPassword, saveButton, clearButtonAccept, clearButtonDeny,
    saveSuccefullyButton, saveWarningAccept, saveWarningDeny; // Buttons

    JLabel textNickNameGenerate, textPasswordGenerate, textNickNameValue, textPasswordValue,
    textLevelsDifficulty, textHelpAboutFirstString, textHelpAboutSecondString, textHelpAboutThirdString,
    textWarningFirstString, textWarningSecondString, defaultPasswordLevel, textPasswordInfoHardLVL, textPasswordInfoNormalLVL, 
    textPasswordInfoEazyLVL, textPasswordInfoPS, textSaveSuccefully, textClearTextFile; // Texts

    JCheckBox autoSaveInTextFile, autoCopyPassword, saveInTextFileWithData, saveInTextFileWithComment;// CheckBoxes

    JComboBox levelsDifficulty; // Combobox

    JTextField saveAddComment; // Text field



    // DEFAULT VALUES

    

    String generateNickNameDefault = "Emperty";
    String generatePasswordDefault = "Emperty";
    String textPasswordLevelDefault = "hard";

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

    int mainWindowSizeW = 600, mainWindowSizeH = 300;

    int warningWindowSizeW = 400, warningWindowSizeH = 120; // 400 110

    int passwordInfoWindowSizeW = 600, passwordInfoWindowSizeH = 150;

    int saveWindowSizeW = 500, saveWindowSizeH = 100;

    int saveSucceffulyWindowSizeW = 230, saveSucceffulyWindowSizeH = 100;

    int clearTextFileAcceptW = 500, clearTextFileAcceptH = 100;
   
    int sizeMainWindowW = (screenSize.width - mainWindowSizeW) / 2, sizeMainWindowH = (screenSize.height - mainWindowSizeH) / 2;

    int sizeWarningWindowW = (screenSize.width - warningWindowSizeW) / 2, sizeWarningWindowH = (screenSize.height - warningWindowSizeH) / 2;

    int sizePIWW = (screenSize.width - passwordInfoWindowSizeW) / 2, sizePIWH = (screenSize.height - passwordInfoWindowSizeH) / 2;

    int sizeSaveWindowW = (screenSize.width - saveWindowSizeW) / 2, sizeSaveWindowH = (screenSize.height - saveWindowSizeH) / 2;

    int sizeSSWW = (screenSize.width - saveSucceffulyWindowSizeW) / 2, sizeSSWH = (screenSize.height - saveSucceffulyWindowSizeH) / 2;

    int sizeCTFAW = (screenSize.width - clearTextFileAcceptW) / 2, sizeCTFAH = (screenSize.height - clearTextFileAcceptH) / 2;

    AMLGUI()
    {

        // DIFFICULTY LEVELS CONFIGURATION
        String defaultLevel;

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

        autoSaveInTextFile = new JCheckBox("Auto save", true);
        settingsMenu.add(autoSaveInTextFile);

        autoCopyPassword = new JCheckBox("Auto copy password", true);
        settingsMenu.add(autoCopyPassword);

        saveInTextFileWithData = new JCheckBox("Save with data", true);
        settingsMenu.add(saveInTextFileWithData);

        saveInTextFileWithComment = new JCheckBox("Save with comment", true);
        settingsMenu.add(saveInTextFileWithComment);

        defaultPasswordLevel = new JLabel("Default password level: " + textPasswordLevelDefault);
        settingsMenu.add(defaultPasswordLevel);

        mainMenuBar.add(settingsMenu);

        // HELP

        helpMenu = new JMenu("Help");
        textHelpAboutFirstString = new JLabel("This programm generating random password and nickname");
        textHelpAboutSecondString = new JLabel("You can save got info in text file (accounts.txt)");
        textHelpAboutThirdString = new JLabel("You can castomize default settings in config.ini");
        helpAboutPassword = new JMenuItem("You can see password difficulty levels -->");
        helpMenu.add(textHelpAboutFirstString);
        helpMenu.add(textHelpAboutSecondString);
        helpMenu.add(textHelpAboutThirdString);
        helpMenu.add(helpAboutPassword);
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

        debugMenu = new JMenu("Debug");

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

        mainMenuBar.add(debugMenu);



        mainWindow.setJMenuBar(mainMenuBar);

        // MAIN WINDOW

        textNickNameGenerate = new JLabel("Your random nickname:");
        textNickNameGenerate.setBounds(10, 10, 500, 20);
        mainWindow.add(textNickNameGenerate);

        copyGeneratedNickName = new JButton("COPY");
        copyGeneratedNickName.setBounds(180, 10, 75, 20);
        mainWindow.add(copyGeneratedNickName);

        textNickNameValue = new JLabel(generateNickNameDefault);
        textNickNameValue.setBounds(20, 30, 500, 20);
        mainWindow.add(textNickNameValue);

        textPasswordGenerate = new JLabel("Your random password:");
        textPasswordGenerate.setBounds(10, 50, 500, 20);
        mainWindow.add(textPasswordGenerate);

        copyGeneratedPassword = new JButton("COPY");
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
                    Desktop.getDesktop().browse(new URI("vk.com/volodyahoi"));
                }
                catch (Exception f) {
                    throw new RuntimeException(f);
                }
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



        mainWindow.setSize(mainWindowSizeW, mainWindowSizeH);
        mainWindow.setBounds(sizeMainWindowW, sizeMainWindowH, mainWindowSizeW, mainWindowSizeH);
        mainWindow.setLayout(null);
        mainWindow.setVisible(true);
    }

    public static void main (String args[]) throws IOException {

        new AMLGUI();      

    }
}

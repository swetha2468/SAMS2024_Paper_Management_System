package app.gui;

import backend.enums.Role;
import backend.webinterface.Administrator;
import backend.webinterface.User;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


public class AdministratorController implements Initializable
{

    @FXML
    private TableView<users> userList;
    @FXML
    private TableColumn <users, Integer> col_id;
    @FXML
    private TableColumn <users, String> col_username;
    @FXML
    private TableColumn <users, String> col_pass;
    @FXML
    private TableColumn <users, String> col_role_1;
    @FXML
    private TableColumn <users, String> col_firstname;
    @FXML
    private TableColumn <users, String> col_lastname;
    @FXML
    private TableColumn <users, String> col_email;
    @FXML
    private TableColumn <users, String> col_phone;
    @FXML
    private TableColumn <users, String> col_address;

    @FXML
    TextField usernametextbox,passwordtextbox,firstnametextbox, lastnametextbox, emailtextbox, phonetextbox, addresstextbox;

    @FXML
    ComboBox<Role> rolecombobox;

    Administrator adminInstance;
    ObservableList<users> currentState;
    static boolean listChanged = false;

    static users createUser;


    @FXML
    private void Logout() throws IOException
    {
        App.GetWebInterface().getUser().Logout();
        App.setRoot("login");
    }

    @FXML
    private void AccountManagement() throws IOException
    {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("accountManagement");
    }

    @FXML
    private void ConferenceManagement() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void TemplateManagement() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }
    @FXML
    private void AddAccount() throws Exception {
        createUser = new users(0, usernametextbox.getText(), passwordtextbox.getText(), rolecombobox.getValue() == null ? Role.submitter.name() : rolecombobox.getValue().name(), firstnametextbox.getText(), lastnametextbox.getText(), emailtextbox.getText(), phonetextbox.getText(), addresstextbox.getText());
        if(!CheckForEmpty())
        {
            try {
                adminInstance.CreateAccount(createUser);
                App.setRoot("accountManagement");
                createUser = null;
            }
            catch(Exception err)
            {
                App.setErrorCondition(err.getMessage());
                App.setRoot("error");
            }

        }
        else
        {
            App.setErrorCondition("A field has been left incomplete, please enter all details before continuing");
            App.setRoot("error");
        }
    }

    private boolean CheckForEmpty()
    {
        if(usernametextbox.getText().isEmpty() || passwordtextbox.getText().isEmpty() || firstnametextbox.getText().isEmpty() || lastnametextbox.getText().isEmpty() ||  rolecombobox.getItems().isEmpty() || emailtextbox.getText().isEmpty() || phonetextbox.getText().isEmpty() || addresstextbox.getText().isEmpty())
        {
            return true;
        }
        return false;
    }
    @FXML
    private void Back() throws IOException
    {
        App.setRoot("accountManagement");
    }

    @FXML
    private void CreateAccount() throws IOException
    {
        App.setRoot("administratorCreateAccount");
    }
    @FXML
    private void Search() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void Submit() throws IOException {
        /* TODO : HOOKUP TO BACKEND */
        App.setRoot("login");
    }

    @FXML
    private void DeleteAccount() throws Exception {

        users user = userList.getSelectionModel().getSelectedItem();
        if(user.getUserId() != adminInstance.GetUser().GetUserId()) {
            adminInstance.DeleteAccount(user.getUsername());
            Display();
        }
        else
        {
            App.setErrorCondition("Cannot delete your admin own account.");
            App.setRoot("error");
        }
        /* TODO : HOOKUP TO BACKEND */
        //App.setRoot("login");
    }

    private void Display()
    {
        ObservableList<users> accounts;
        try {
                accounts = App.GetWebInterface().getUser().getAdminInstance().GetAccounts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        //username, password, primary_role, firstname, lastname, email, phone, address;
            col_id.setCellValueFactory(new PropertyValueFactory<>("userId"));

            //WE DONT WANT TO EDIT THE USER ID
            //col_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            //col_id.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, Integer>>()
            //{
            //    @Override
            //    public void handle(TableColumn.CellEditEvent<users, Integer> usersIntegerCellEditEvent)
            //    {
            //        listChanged = true;
            //        users user = usersIntegerCellEditEvent.getRowValue();
            //        user.setUserId(usersIntegerCellEditEvent.getNewValue());
            //        ModifyAccount(user);
            //    }
            //});

            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_username.setCellFactory(TextFieldTableCell.forTableColumn());

            col_username.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent)
                {
                    users user = usersStringCellEditEvent.getRowValue();
                    user.setUsername(usersStringCellEditEvent.getNewValue());
                    try {
                        ModifyAccount(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });


            col_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
            col_pass.setCellFactory(TextFieldTableCell.forTableColumn());
        col_pass.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent)
            {
                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setPassword(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });


            col_role_1.setCellValueFactory(new PropertyValueFactory<>("primary_role"));
            col_role_1.setCellFactory(TextFieldTableCell.forTableColumn());

        col_role_1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent)
            {
                users user = usersStringCellEditEvent.getRowValue();
                user.setPrimary_role(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_email.setCellFactory(TextFieldTableCell.forTableColumn());

        col_email.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent) {

                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setEmail(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });


        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            col_firstname.setCellFactory(TextFieldTableCell.forTableColumn());

        col_firstname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent) {

                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setFirstname(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

            col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            col_lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lastname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent) {

                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setLastname(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });



        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            col_phone.setCellFactory(TextFieldTableCell.forTableColumn());

        col_phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent) {

                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setPhone(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

            col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            col_address.setCellFactory(TextFieldTableCell.forTableColumn());

        col_address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<users, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<users, String> usersStringCellEditEvent) {

                listChanged = true;
                users user = usersStringCellEditEvent.getRowValue();
                user.setAddress(usersStringCellEditEvent.getNewValue());
                try {
                    ModifyAccount(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        userList.setItems(accounts);

    }
    @FXML
    private void ModifyAccount() throws Exception {
        users user = userList.getSelectionModel().getSelectedItem();
        ModifyAccount(user);
    }
    private void ModifyAccount(users user) throws Exception {

        try
        {
            adminInstance.ModifyAccount(user.getUserId(), user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), Role.valueOf(user.getPrimary_role()), user.getEmail(), user.getPhone(), user.getAddress());
            Display();
        }
        catch (IllegalArgumentException ignored)
        {
            App.setErrorCondition("Role must be a valid option pcc, pcm, submitter, administrator");
            App.setRoot("error");
        }
        catch (Exception err)
        {
            App.setErrorCondition(err.getMessage());
            App.setRoot("error");
        }
    }
    @FXML
    private void jumpToToolTip() throws IOException
    {
        App.setRoot("adminHelpTip");
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        adminInstance = App.GetWebInterface().getUser().getAdminInstance();

        if(Objects.equals(App.GetRoot(), "accountManagement"))
        {
            Display();
        }
        if (Objects.equals(App.GetRoot(), "administratorCreateAccount"))
        {
            rolecombobox.getItems().add(Role.administrator);
            rolecombobox.getItems().add(Role.submitter);
            rolecombobox.getItems().add(Role.pcc);
            rolecombobox.getItems().add(Role.pcm);

            rolecombobox.setOnAction((event) -> {
                int selectedIndex = rolecombobox.getSelectionModel().getSelectedIndex();
                Object selectedItem = rolecombobox.getSelectionModel().getSelectedItem();

                System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
                System.out.println("   ComboBox.getValue(): " + rolecombobox.getValue());
            });

            if (createUser != null)
            {
                System.out.println("Getting in here");
                usernametextbox.setText(createUser.getUsername());
                passwordtextbox.setText(createUser.getPassword());
                try {
                    rolecombobox.setValue(Role.valueOf(createUser.getPrimary_role()));
                }
                catch (IllegalArgumentException ignored)
                {
                    rolecombobox.setValue(Role.submitter);
                }
                firstnametextbox.setText(createUser.getFirstname());
                lastnametextbox.setText(createUser.getLastname());
                emailtextbox.setText(createUser.getEmail());
                phonetextbox.setText(createUser.getPhone());
                addresstextbox.setText(createUser.getAddress());
            }

        }
    }
}

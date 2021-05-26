package DAO;

import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO {

    Connection connection;
    ResultSet rs;
    PreparedStatement pst;
    ArrayList<UserDTO> list = new ArrayList<>();

    public ResultSet userAuthentication(UserDTO objtUserDTO) {
        connection = new ConnectionDAO().dbConnection();

        try {
            String sql = "select * from users where login=? and password=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, objtUserDTO.getLogin());
            pst.setString(2, objtUserDTO.getPassword());

            rs = pst.executeQuery();

            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UserDAO " + e);
            return null;
        }
    }

    public void userCreate(UserDTO objtUserDTO) {
        connection = new ConnectionDAO().dbConnection();

        try {
            String sql = "insert into users (login, password, name, roles) values(?,?,?,?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, objtUserDTO.getLogin());
            pst.setString(2, objtUserDTO.getPassword());
            pst.setString(3, objtUserDTO.getName());
            pst.setString(4, objtUserDTO.getRoles());

            pst.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UserDAO " + e);
        }
    }

    public ArrayList<UserDTO> showUsers() {
        connection = new ConnectionDAO().dbConnection();

        try {
            String sql = "select * from users";
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                UserDTO objtUserDdto = new UserDTO();
                objtUserDdto.setLogin(rs.getString("login"));
                objtUserDdto.setName("name");
                objtUserDdto.setPassword("password");
                objtUserDdto.setRoles("roles");

                list.add(objtUserDdto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UserDAO " + e);
            return null;
        }
        return list;
    }

    public ResultSet getUserLogin(UserDTO objtUserDTO) {
        connection = new ConnectionDAO().dbConnection();
        String sql = "select * from users where login=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objtUserDTO.getLogin());

            rs = pst.executeQuery();

            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UserDAO " + e);
            return null;
        }
    }
}

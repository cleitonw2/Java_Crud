package DAO;

import DTO.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserDAO {

    Connection connection;

    public ResultSet userAuthentication(UserDTO objtUserDTO) {
        connection = new ConnectionDAO().dbConnection();

        try {
            String sql = "select * from users where login=? and password=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, objtUserDTO.getLogin());
            pst.setString(2, objtUserDTO.getPassword());

            ResultSet rs = pst.executeQuery();

            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"UserDAO "+ e);
            return null;
        }
    }
}

package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    public Text breakfast;
    public Text lunch;
    public Text dinner;
    private DatabaseConnection databaseConnection;
    @FXML
    private Text maleACTotal;

    @FXML
    private Text maleACAvailable;

    @FXML
    private Text maleNonACTotal;

    @FXML
    private Text maleNonACAvailable;

    @FXML
    private Text femaleACTotal;

    @FXML
    private Text femaleACAvailable;

    @FXML
    private Text femaleNonACTotal;

    @FXML
    private Text femaleNonACAvailable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                try {

            databaseConnection = new DatabaseConnection();

            String sql = "SELECT 'male' as gender, SUM(singleAC) as singleAC, SUM(singleNonAC) as singleNonAC, SUM(sharedAC) as sharedAC, SUM(sharedNonAC) as sharedNonAC FROM halls WHERE maleOrFemale = 'male' GROUP BY gender UNION SELECT 'female' as gender, SUM(singleAC) as singleAC, SUM(singleNonAC) as singleNonAC, SUM(sharedAC) as sharedAC, SUM(sharedNonAC) as sharedNonAC FROM halls WHERE   maleOrFemale = 'female' GROUP BY gender;";
            ResultSet resultSet = databaseConnection.queryData(sql);
            String sql2 = "SELECT 'male' as gender,SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedACSingleRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedNonACSingleRooms, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedACSharedRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedNonACSharedRooms FROM (SELECT * FROM rooms INNER JOIN halls ON rooms.hall_id = halls.id AND halls.maleOrFemale = 'male') as rooms WHERE bookedBy IS NOT NULL UNION SELECT 'female' as gender, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedACSingleRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedNonACSingleRooms, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedACSharedRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedNonACSharedRooms\n" +
                    "FROM (SELECT * FROM rooms INNER JOIN halls ON rooms.hall_id = halls.id AND halls.maleOrFemale = 'female') as rooms WHERE bookedBy IS NOT NULL;";
            String sql3 = "SELECT SUM(breakfast) as total_breakfast, SUM(lunch) as total_lunch, SUM(dinner) as total_dinner FROM meals WHERE date = CURRENT_DATE()";
            ResultSet resultSet2 = databaseConnection.queryData(sql2);
            ResultSet resultSet3 = databaseConnection.queryData(sql3);

            while(resultSet.next() && resultSet2.next() && resultSet3.next())
            {
                if(resultSet.getString(1).equals("male")) {

                    int totalACRoomBooked = resultSet2.getInt(2)+resultSet2.getInt(4);
                    int totalACRoom = resultSet.getInt(2)+resultSet.getInt(4);

                    int totalNonACRoomBooked = resultSet2.getInt(3)+resultSet2.getInt(5);
                    int totalNonACRoom = resultSet.getInt(3)+resultSet.getInt(5);
                    maleACTotal.setText(String.valueOf(totalACRoom));
                    maleNonACTotal.setText(String.valueOf(totalNonACRoom));
                    maleACAvailable.setText(String.valueOf(totalACRoom-totalACRoomBooked));
                    maleNonACAvailable.setText(String.valueOf(totalNonACRoom-totalNonACRoomBooked));
                    breakfast.setText(String.valueOf(resultSet3.getInt(1)));
                    lunch.setText(String.valueOf(resultSet3.getInt(2)));
                    dinner.setText(String.valueOf(resultSet3.getInt(3)));
                }
                {

                    int totalACRoomBooked = resultSet2.getInt(2)+resultSet2.getInt(4);
                    int totalACRoom = resultSet.getInt(2)+resultSet.getInt(4);

                    int totalNonACRoomBooked = resultSet2.getInt(3)+resultSet2.getInt(5);
                    int totalNonACRoom = resultSet.getInt(3)+resultSet.getInt(5);
                    femaleACTotal.setText(String.valueOf(totalACRoom));
                    femaleNonACTotal.setText(String.valueOf(totalNonACRoom));
                    femaleACAvailable.setText(String.valueOf(totalACRoom-totalACRoomBooked));
                    femaleNonACAvailable.setText(String.valueOf(totalNonACRoom-totalNonACRoomBooked));
                }
            }





        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

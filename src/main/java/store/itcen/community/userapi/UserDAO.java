package store.itcen.community.userapi;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String joinDate;

    private Connection con;
    private ResultSet rs;

    public UserDAO() {
        try {
            String dbURL = "jdbc:mariadb://localhost:3306/communitydb?characterEncoding=UTF-8&serverTimezone=UTC";
            String dbID = "community";
            String dbPassword = "1111";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그인
    /*
     * -2: 아이디없음
     * -1: 서버오류
     * 0: 비밀번호 틀림
     * 1: 성공
     */
    public int login(String email, String password) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT userPassword FROM user WHERE userID = ?");
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString(1).equals(password) ? 1 : 0;
            } else {
                return -2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 중복여부 확인
    public boolean ID_Check(String email) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 회원가입
    /*
     * -1: 서버오류
     * 0: 이미 존재하는 아이디
     * 1: 성공
     */
    public int join(UserDAO userDAO) {
        if(!ID_Check(userDAO.getEmail())) return 0;

        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO user VALUES (?,?,?,?,?)");
            pst.setString(1, userDAO.getEmail());
            pst.setString(2, userDAO.getPassword());
            pst.setString(3, userDAO.getName());
            pst.setString(4, userDAO.getNickname());
            pst.setString(5, userDAO.getJoinDate());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 유저 데이터 가져오기
    public UserDAO getUser(String userID) {
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
            pst.setString(1, userID);
            rs = pst.executeQuery();
            if (rs.next()) {
                UserDAO userDAO = new UserDAO();
                userDAO.setEmail(rs.getString(1));
                userDAO.setPassword(rs.getString(2));
                userDAO.setName(rs.getString(3));
                userDAO.setNickname(rs.getString(4));
                userDAO.setJoinDate(rs.getString(5));
                return userDAO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
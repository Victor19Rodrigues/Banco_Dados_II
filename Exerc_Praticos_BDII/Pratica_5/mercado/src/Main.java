
import java.sql.SQLException;
import control.*;
import model.Produto;
import view.viewPrincipal;

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        ctrPrincipal ctrPrinc = new ctrPrincipal();
        ctrPrinc.run();
    }
}

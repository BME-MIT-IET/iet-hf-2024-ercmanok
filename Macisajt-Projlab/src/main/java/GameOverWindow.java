import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * GameOverWindow osztály, a játék végét jelző ablakot reprezentálja
 */
public class GameOverWindow extends JPanel {

    /**
     * A lehetséges végeredményeket tartalmazó felsorolás típus
     */
    enum Winner {
        PLUMBERS, SABOTEURS, DRAW
    }

    /**
     * GameOverWindow konstruktor
     *
     * @param winner a végeredmény
     */
    public GameOverWindow(Winner winner) {
        initComponents(winner);
    }

    /**
     * Inicializálja a GameOverWindow komponenseit
     *
     * @param winner a végeredmény
     */
    private void initComponents(Winner winner) {
        JPanel panel = new JPanel();
        JLabel lTitle = new JLabel();
        JLabel lResult1 = new JLabel();
        JLabel lResult2 = new JLabel();
        JLabel lTeam = new JLabel();
        JButton bBackToMain = new JButton();
        String fontStandard = "Segoe UI";
        String fontBold = "Segoe UI Semibold";
        panel.setBackground(View.PRIMARY_COLOR);
        panel.setForeground(View.SECONDARY_COLOR);

        lTitle.setFont(new Font(fontStandard, Font.BOLD, 60));
        lTitle.setForeground(View.SECONDARY_COLOR);
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        switch (winner) {
            case PLUMBERS -> lTitle.setText("<html><center>GAME OVER<br><br>A szerelők győztek</html>");
            case SABOTEURS -> lTitle.setText("<html><center>GAME OVER<br><br>A szabotőrök győztek</html>");
            case DRAW -> lTitle.setText("<html><center>GAME OVER<br><br>Döntetlen</html>");
        }

        lResult1.setFont(new Font(fontStandard, Font.BOLD, 35));
        lResult1.setForeground(View.SECONDARY_COLOR);
        lResult1.setHorizontalAlignment(SwingConstants.CENTER);
        lResult1.setText("Összegyűjtött vízmennyiség: " + Game.Instance.pipelineSystem.getCollectedWater());

        lResult2.setFont(new Font(fontStandard, Font.BOLD, 35));
        lResult2.setForeground(View.SECONDARY_COLOR);
        lResult2.setHorizontalAlignment(SwingConstants.CENTER);
        lResult2.setText("Kifolyt vízmennyiség: " + Game.Instance.pipelineSystem.getLeakedWater());

        lTeam.setFont(new Font(fontBold, Font.ITALIC, 18));
        lTeam.setForeground(View.SECONDARY_COLOR);
        lTeam.setHorizontalAlignment(SwingConstants.CENTER);
        lTeam.setText("macisajt csapat");

        bBackToMain.setBackground(View.SECONDARY_COLOR);
        bBackToMain.setFont(new Font(fontBold, Font.PLAIN, 36));
        bBackToMain.setForeground(View.PRIMARY_COLOR);
        bBackToMain.setText("VISSZA A FŐMENÜBE");
        bBackToMain.addActionListener(this::bBackToMainActionPerformed);

        // Generated by NetBeans
        GroupLayout jPanel2Layout = new GroupLayout(panel);
        panel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lTeam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lResult1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lResult2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(bBackToMain, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(lTitle)
                                .addGap(40, 40, 40)
                                .addComponent(lResult1)
                                .addComponent(lResult2)
                                .addGap(18, 18, 18)
                                .addComponent(bBackToMain, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(lTeam, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
        );
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        // End of generated code
    }

    /**
     * A "Vissza a főmenübe" gomb megnyomását kezeli
     *
     * @param evt a gomb megnyomásakor kiváltódott esemény
     */
    private void bBackToMainActionPerformed(ActionEvent evt) {
        View.setContentPane(new MainMenuWindow());
    }
}

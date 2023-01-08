import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SelectFile extends JFrame {
    JFrame frm = new JFrame("Select File");
    String filePath = ""; // 사진 경로
    String fileName = ""; // 사진 이름
    Toolkit toolkit = getToolkit(); // 이미지를 가져오기 위해서

    String id;

    SelectFile(String userid){
        id= userid;
        // 전체 프레임
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);

        // 상단 ------------------------------------------------------------------
        JPanel upper = new JPanel();
        upper.setPreferredSize(new Dimension(700,400));
        upper.setBackground(Color.WHITE);

        // 중앙 ------------------------------------------------------------------
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(700,200));
        center.setBackground(Color.WHITE);

        JButton selectButton = new JButton("Select File"); // 파일 선택 버튼
        selectButton.setPreferredSize(new Dimension(200, 100));
        selectButton.addActionListener(new OpenActionListener());
        selectButton.setBackground(Color.WHITE);

        center.add(selectButton);

        // 하단 ------------------------------------------------------------------
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(700,400));
        bottom.setBackground(Color.WHITE);


        // 전체 프레임에 추가
        contentPane.add(upper, BorderLayout.NORTH);
        contentPane.add(center, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        frm.add(contentPane);
        frm.setVisible(true);
    }


    class OpenActionListener implements ActionListener {
        JFileChooser chooser;
        OpenActionListener(){
            chooser = new JFileChooser();
        }
        public void actionPerformed(ActionEvent e){
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);

            int ret = chooser.showOpenDialog(null);
            if(ret != JFileChooser.APPROVE_OPTION){
                JOptionPane.showMessageDialog(null, "No file selected", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            filePath = chooser.getSelectedFile().getPath(); // 선택한 파일 경로
            fileName = chooser.getSelectedFile().getName();


            new AddPostContent(id, filePath, fileName);
            frm.setVisible(false);
        }
    }


    public static void main(String[] args) {
        new SelectFile("database12");
    }
}

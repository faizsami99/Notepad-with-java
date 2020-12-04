import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;


class Editor extends JFrame
{
    Font font = new Font("", Font.BOLD, 20);
    JTextArea text;
    JLabel bottom;
    String filename;
    String[] arrS;
    File fl;
    DataOutputStream os;
    FileOutputStream fo;

    public Editor()
    {
        setTitle("Text Editor");
//        setIconImage(new ImageIco/typewriter.png/
        GuiAppear();
        setVisible(true);
        setSize(650,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void GuiAppear()
    {

        setLayout(new BorderLayout());
        JMenuBar mb = new JMenuBar();

        JMenu file = new JMenu("Menu");
        JMenuItem open = new JMenuItem("open");
        JMenuItem save = new JMenuItem("save");
        JMenuItem saveAs = new JMenuItem("save as");

        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("copy");
        JMenuItem cut = new JMenuItem("cut");
        JMenuItem paste = new JMenuItem("paste");
        JMenuItem selectAll = new JMenuItem("select All");

        open.addActionListener(ae->
        {
            JFileChooser fc = new JFileChooser(".");
            int i = fc.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION)
            {
                File f = fc.getSelectedFile();
                String filepath = f.getPath();
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader(filepath));
                    int s1;
                    String op;
                    StringBuilder s2= new StringBuilder();
                    while ((s1=br.read())!=-1)
                    {
                        op = String.valueOf((char) s1);
                        s2.append(op);

                    }
                    text.setText(s2.toString());
                    fc.setCurrentDirectory(new File("."));
                    br.close();
                } catch (Exception ignored){}
            }
        });

        save.addActionListener(ae->
        {
//            FileSystemView.getFileSystemView().getHomeDirectory()
            JFileChooser fc = new JFileChooser("./");
            int i = fc.showSaveDialog(null);
//            String s = fc.getName();
//            System.out.println(s);
            if(i==JFileChooser.APPROVE_OPTION)
            {
                try {
                    arrS = fc.getSelectedFile().getAbsolutePath().split("/");
                    filename = arrS[arrS.length-1];
                    fl = new File(filename);
                    fo = new FileOutputStream(fl);
                    os = new DataOutputStream(fo);
                    os.writeChars(text.getText());
                    setTitle(filename);
                }
                catch (Exception ignored){}
            }
            else{
                text.setText("Something went wrong");
            }
        });

        saveAs.addActionListener(ae->
        {
            filename = super.getTitle();
            fl = new File(filename);
            try {
                fo = new FileOutputStream(fl);
                os = new DataOutputStream(fo);
                os.writeChars(text.getText());
            }catch (Exception ignored){}
        });

        copy.addActionListener(ae-> text.copy());

        cut.addActionListener(ae-> text.cut());

        paste.addActionListener(ae-> text.paste());

        selectAll.addActionListener(ae-> text.selectAll());

        file.add(open);
        file.add(save);
        file.add(saveAs);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        mb.add(file);
        mb.add(edit);

        super.add(mb, BorderLayout.NORTH);
        text = new JTextArea();
        text.setFont(font);
        text.setLineWrap(true);
        //text.setBackground(Color.BLACK);
        //text.setForeground(Color.WHITE);
        //super.add(text, BorderLayout.CENTER);
        super.add(new JScrollPane(text), BorderLayout.CENTER);
        super.add(new JScrollPane(text), BorderLayout.CENTER);

        JLabel east = new JLabel("  ");
        super.add(east, BorderLayout.EAST);

        JLabel west = new JLabel("  ");
        //west.setBackground(Color.GRAY);
        super.add(west, BorderLayout.WEST);

        bottom = new JLabel("   Notepad");
        super.add(bottom, BorderLayout.SOUTH);
    }
}
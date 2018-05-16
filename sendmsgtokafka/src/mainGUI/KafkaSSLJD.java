package mainGUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kafka.BaseMsg;

public class KafkaSSLJD extends JDialog implements ActionListener {
	public JTextField jtf1, jtf2, jtf3, jtf4, jtf5;
	public JButton button1, button2, button3, button4, button5;
	public JPasswordField jpf1, jpf2, jpf3;
	public JLabel jlb1, jlb2, jlb3, jlb4, jlb5;
	public JFileChooser chooseFile1, chooseFile2;
	public SSLParams sslp;
	// 发送还是接收标志
	public int f;
	public JCheckBox jcbox;
	public boolean isSure = false;
	public Utils utils = new Utils();

	public KafkaSSLJD(SSLParams sp, int f, JCheckBox jcbox) {
		// 初始化参数
		this.sslp = sp;
		this.f = f;
		this.jcbox = jcbox;
		init();
	}

	// private void initPro() {
	// // 原理：首先判断exe所在目录是否存在隐藏的配置文件kafkamodel.properties，
	// // 如果存在，则读取此配置文件，如果不存在则生成一个默认的隐藏的配置文件
	// if (f == 1) {
	//
	// } else if (f == 2) {
	//
	// }
	// BaseMsg km = new BaseMsg();
	// File f = new File("sendkafkamodel.properties");
	// if (utils.isFileExit(f)) {
	// // 获取默认配置信息
	// try {
	// km = utils.getDefaultMsg(f, 0);
	// } catch (Exception e1) {
	// km = new BaseMsg();
	// e1.printStackTrace();
	// }
	// }
	//
	// BaseMsg kmm = new BaseMsg();
	// File ff = new File("consumerkafkamodel.properties");
	// if (utils.isFileExit(ff)) {
	// // 获取默认配置信息
	// try {
	// kmm = utils.getDefaultMsg(ff, 1);
	// } catch (Exception e1) {
	// kmm = new BaseMsg();
	// e1.printStackTrace();
	// }
	// }
	// }

	private void init() {
		this.setLayout(null);
		jlb1 = new JLabel("*Truststore Location：");
		jlb1.setBounds(18, 20, 120, 25);
		this.add(jlb1);

		jtf1 = new JTextField(10);
		jtf1.setText(sslp.getTruststoreLocation().replaceAll("\\\\\\\\", "\\\\") != null ? sslp.getTruststoreLocation().replaceAll("\\\\\\\\", "\\\\") : "");
		jtf1.setBounds(150, 20, 240, 25);
		this.add(jtf1);
		jtf1.setEditable(false);

		button1 = new JButton("选择");
		button1.setBounds(400, 20, 60, 25);
		this.add(button1);
		button1.addActionListener(this);

		jlb2 = new JLabel("*Truststore Password：");
		jlb2.setBounds(18, 50, 120, 25);
		this.add(jlb2);

		jpf1 = new JPasswordField(20);
		jpf1.setBounds(150, 50, 240, 25);
		jpf1.setText(sslp.getTruststorePwd() != null ? sslp.getTruststorePwd() : "");
		this.add(jpf1);

		jlb3 = new JLabel("*Keystore Location：");
		jlb3.setBounds(18, 80, 120, 25);
		this.add(jlb3);

		jtf2 = new JTextField(10);
		jtf2.setText(sslp.getKeystoreLocation().replaceAll("\\\\\\\\", "\\\\") != null ? sslp.getKeystoreLocation().replaceAll("\\\\\\\\", "\\\\") : "");
		jtf2.setBounds(150, 80, 240, 25);
		this.add(jtf2);
		jtf2.setEditable(false);

		button2 = new JButton("选择");
		button2.setBounds(400, 80, 60, 25);
		this.add(button2);
		button2.addActionListener(this);

		jlb4 = new JLabel("*Keystore Password：");
		jlb4.setBounds(18, 110, 120, 25);
		this.add(jlb4);

		jpf2 = new JPasswordField(20);
		jpf2.setBounds(150, 110, 240, 25);
		jpf2.setText(sslp.getKeystorePwd() != null ? sslp.getKeystorePwd() : "");
		this.add(jpf2);

		jlb5 = new JLabel("*Key Password：");
		jlb5.setBounds(18, 140, 120, 25);
		this.add(jlb5);

		jpf3 = new JPasswordField(20);
		jpf3.setBounds(150, 140, 240, 25);
		jpf3.setText(sslp.getKeyPwd() != null ? sslp.getKeyPwd() : "");
		this.add(jpf3);

		button3 = new JButton("确定");
		button3.setBounds(100, 200, 80, 25);
		this.add(button3);
		button3.addActionListener(this);

		button4 = new JButton("取消");
		button4.setBounds(320, 200, 80, 25);
		button4.addActionListener(this);
		this.add(button4);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("setup.png"));
		this.setIconImage(imageIcon.getImage());
		// 原窗口不能动(模态窗口)
		this.setModal(true);
		this.setTitle("设置SSL证书及密码");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// 监听界面关闭事件
		this.addWindowListener(new WindowAdapter() {
			// 当关闭时
			// 注意弹窗的关闭监听不是windowsclosing而是windowDeactivated
			public void windowDeactivated(WindowEvent e) {
				if (!isSure) {
					if (f == 1) {
						KafkaMainGUI.sp1 = new SSLParams();
					} else if (f == 2) {
						KafkaMainGUI.sp2 = new SSLParams();
					}
					jcbox.setSelected(false);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 监听选择文件
		Object key = e.getSource();
		if (key.equals(button1)) {
			// 打开文件选择器
			chooseFile1 = new JFileChooser();
			// 单选
			chooseFile1.setMultiSelectionEnabled(false);
			// 只能选择文件
			chooseFile1.setFileSelectionMode(0);
			int returnVal = chooseFile1.showOpenDialog(this);
			if (returnVal == chooseFile1.APPROVE_OPTION) {
				// 获取所有选中的文件
				File f = chooseFile1.getSelectedFile();
				jtf1.setText(f.getAbsolutePath());
			}
		}

		if (key.equals(button2)) {
			// 打开文件选择器
			chooseFile2 = new JFileChooser();
			// 单选
			chooseFile2.setMultiSelectionEnabled(false);
			// 只能选择文件
			chooseFile2.setFileSelectionMode(0);
			int returnVal = chooseFile2.showOpenDialog(this);
			if (returnVal == chooseFile2.APPROVE_OPTION) {
				// 获取所有选中的文件
				File f2 = chooseFile2.getSelectedFile();
				jtf2.setText(f2.getAbsolutePath());
			}
		}

		// 监听保存按钮
		if (e.getSource().equals(button3)) {
			// 获取参数
			sslp = getSSLParames();
			// 验证
			if (checkParams(sslp)) {
				// 赋值给静态变量
				if (f == 1) {
					KafkaMainGUI.sp1 = sslp;
				} else if (f == 2) {
					KafkaMainGUI.sp2 = sslp;
				}
				isSure = true;
				dispose();
			} else {
				sslp = new SSLParams();
				JOptionPane.showMessageDialog(null, "不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
			}
		}

		// 监听取消按钮
		if (e.getSource().equals(button4)) {
			isSure = false;
			// // 获取参数
			// sslp = getSSLParames();
			// // 验证
			// if (!checkParams(sslp)) {
			// jcbox.setSelected(false);
			// }
			if (f == 1) {
				KafkaMainGUI.sp1 = new SSLParams();
			} else if (f == 2) {
				KafkaMainGUI.sp2 = new SSLParams();
			}
			jcbox.setSelected(false);
			dispose();
		}
	}

	// 获取参数
	private SSLParams getSSLParames() {
		SSLParams ssp = new SSLParams();
		ssp.setKeystoreLocation(jtf2.getText().trim().replaceAll("\\\\", "\\\\\\\\"));
		ssp.setKeystorePwd(jpf2.getText().trim());

		ssp.setTruststoreLocation(jtf1.getText().trim().replaceAll("\\\\", "\\\\\\\\"));
		ssp.setTruststorePwd(jpf1.getText().trim());

		ssp.setKeyPwd(jpf3.getText().trim());
		return ssp;
	}

	// 初步校验修改信息
	private boolean checkParams(SSLParams sslp2) {
		if ("".equals(sslp2.getKeystoreLocation()) || "".equals(sslp2.getKeystorePwd())
				|| "".equals(sslp2.getTruststoreLocation()) || "".equals(sslp2.getTruststorePwd())
				|| "".equals(sslp2.getKeyPwd())) {
			return false;
		}
		return true;
	}

}

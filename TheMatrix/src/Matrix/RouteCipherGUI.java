package Matrix;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class RouteCipherGUI {

	 private JFrame frmTheMatrix;
	    private JLabel instructionlabel;
	    private static Timer timer;
	    private static int index;
	    public static boolean encrypting = false;
	    public static boolean decrypting = false;
	    public static int textL = 0;
	    public static int side = 0;
	    public static String AllText;
	    public static String input;
	    
	    
    public static void main(String[] args) {
        new RouteCipherGUI();
    }

    public RouteCipherGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                			
                JFrame frame = new JFrame("The Matrix");
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setBackground(Color.BLACK);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setLayout(new BorderLayout());
                
                hello = new CustomTextArea();
                hello.setBounds(0, 0, 484, 461);
                hello.setForeground(Color.CYAN);
                
                hello.setFont(new Font("Consolas", Font.PLAIN, 15));
                
                
                hello.setMinimumSize(new Dimension(0, 50));
        		hello.setMargin(new Insets(0, 0, 25, 25));
        		hello.setBackground(new Color (0,0,0));
        		
                hello.setWrapStyleWord(true); 
                
                hello.setCaretColor(Color.GREEN);
                
          
              
                
                hello.setLineWrap(true);
                slowPrint("\n" + " " + ">This is a government simulation, all copied or shared content will instantly be assumed as treason. Use encrypt, decrypt, menu, or letterBlock. You may begin below\n" + " ");
                hello.addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyPressed(KeyEvent e) {
                		
                		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                			input = hello.getText().substring(hello.getText().length() - (hello.getText().length() - AllText.length()));
                			
                			if(input.equals("letterBlock")) {
                				slowPrint(">Rows: " + side + "\n" + ">Columns: " + side + "\n" + ">Area: " + (side *side) + "\n");
                				
                			}
                			
                			else if(encrypting) {
                				side = (int)Math.sqrt(Math.sqrt(input.length()));               				
                				slowPrint(">Encryption: " + encrypt(input) + "\n");
                				//hello.setText("");
                				encrypting = false;
                				
                			}
                			else if(decrypting) {
                				slowPrint(">Decryption: " + decrypt(input) + "\n");
                				//hello.setText("");
                				decrypting = false;
                				
                			}
                			
                			
                			else if(input.equals("encrypt")) {
                				//hello.setText("");
                				slowPrint(">Please enter string below:" + "\n");
                				encrypting = true;
                				decrypting = false;
                				
                			}
                			else if(input.equals("decrypt")) {
                				slowPrint("Please copy and enter string below:" + "\n");
                				encrypting = false;
                				decrypting = true;
                				
                			}
                			else if(input.equals("menu")) {
                				//hello.setText("");
                				
                				slowPrint(">Use encrypt, decrypt, menu, or letterBlock." + "\n");
                			}
                			else{
                				//hello.setText("");
                				
                				slowPrint(">Invalid Command!" + "\n");
                			}
                		}
                	}
                });
                
                hello.addKeyListener(new KeyAdapter() {
                	int cnt = 0;
                	public void keyPressed(KeyEvent e) {
                		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                				cnt++;
                			}
                		
                		if(cnt == 25) {
                			hello.setText("");
                			cnt = 0;
                		}
                		}
                	});
                
                
                
                frame.getContentPane().add(hello);
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                
            }

        });
    }
    
   private JTextArea hello;
   
    public void slowPrint(final String message) {

		if(timer != null && timer.isRunning()) return;
        index   = 0;

        timer = new javax.swing.Timer(20,new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	AllText = hello.getText();
            	hello.setEditable(false);
            	hello.setText(hello.getText() + String.valueOf(message.charAt(index)));
                index++;
                
                if (index >= message.length()) {
                    timer.stop();
                    hello.setEditable(true);
                }
                AllText = hello.getText();
            }
        });
        timer.start();
        AllText = hello.getText();
        hello.setCaretPosition(hello.getText().length());
        AllText = hello.getText();
        
    }   
    
    public static String encrypt(String s) {
        String encryption = "";
    	//StringBuilder encryption = new StringBuilder();
        double sqrt = Math.sqrt(Math.sqrt(s.length()));
        int block = ((int) sqrt) * ((int) sqrt);
        int numA = block - (s.length() % block);
        if(s.length() % block == 0) {
            numA = 0;
        }
        for(int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).equalsIgnoreCase("A")) {
                encryption += ("097");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("B")) {
                encryption += ("098");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("C")) {
                encryption += ("099");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("D")) {
                encryption += ("100");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("E")) {
                encryption += ("101");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("F")) {
                encryption += ("102");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("G")) {
                encryption += ("103");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("H")) {
                encryption += ("104");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("I")) {
                encryption += ("105");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("J")) {
                encryption += ("106");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("K")) {
                encryption += ("107");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("L")) {
                encryption += ("108");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("M")) {
                encryption += ("109");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("N")) {
                encryption += ("110");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("O")) {
                encryption += ("111");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("P")) {
                encryption += ("112");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("Q")) {
                encryption += ("113");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("R")) {
                encryption += ("114");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("S")) {
                encryption += ("115");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("T")) {
                encryption += ("116");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("U")) {
                encryption += ("117");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("V")) {
                encryption += ("118");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("W")) {
                encryption += ("119");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("X")) {
                encryption += ("120");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("Y")) {
                encryption += ("121");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("Z")) {
                encryption += ("122");
            } else if (s.substring(i, i + 1).equalsIgnoreCase(".")) {
                encryption += ("123");
            } else if (s.substring(i, i + 1).equalsIgnoreCase(" ")) {
                encryption += ("000");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("?")) {
                encryption += ("124");
            } else if (s.substring(i, i + 1).equalsIgnoreCase("!")) {
                encryption += ("125");
            }
            else if (s.substring(i, i + 1).equalsIgnoreCase(",")) {
                encryption += ("126");
            }
        }
        encryption += ("A".repeat(Math.max(0, numA)));

        return encryption.toString();
    }

    public static String decrypt(String s) {
    	String encryption = "";
    	//StringBuilder encryption = new StringBuilder();
        double sqrt = Math.sqrt(Math.sqrt(s.length()));
        int block = ((int) sqrt) * ((int) sqrt);
        int numA = block - (s.length() % block);
        if(s.length() % block == 0) {
            numA = 0;
        }
        int cnt = 0;
        for(int i = 0; i < s.length(); i+= 3) {
            if(cnt == 0) {
                if (s.substring(i, i + 3).equalsIgnoreCase("A")) {
                    encryption += ("A");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("097")) {
                    encryption += ("A");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("098")) {
                    encryption += ("B");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("099")) {
                    encryption += ("C");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("100")) {
                    encryption += ("D");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("101")) {
                    encryption += ("E");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("102")) {
                    encryption += ("F");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("103")) {
                    encryption += ("G");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("104")) {
                    encryption += ("H");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("105")) {
                    encryption += ("I");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("106")) {
                    encryption += ("J");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("107")) {
                    encryption += ("K");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("108")) {
                    encryption += ("L");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("109")) {
                    encryption += ("M");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("110")) {
                    encryption += ("N");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("111")) {
                    encryption += ("O");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("112")) {
                    encryption += ("P");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("113")) {
                    encryption += ("Q");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("114")) {
                    encryption += ("R");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("115")) {
                    encryption += ("S");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("116")) {
                    encryption += ("T");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("117")) {
                    encryption += ("U");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("118")) {
                    encryption += ("V");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("119")) {
                    encryption += ("W");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("120")) {
                    encryption += ("X");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("121")) {
                    encryption += ("Y");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("122")) {
                    encryption += ("Z");
                    cnt++;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("123")) {
                    encryption += (".");
                    cnt = 0;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("000")) {
                    encryption += (" ");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("124")) {
                    encryption += ("?");
                    cnt = 0;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("125")) {
                    encryption += ("!");
                    cnt = 0;
                }
                else if (s.substring(i, i + 3).equalsIgnoreCase("126")) {
                    encryption += (",");
                    cnt = 0;
                }
            } else {
                if (s.substring(i, i + 3).equalsIgnoreCase("A")) {
                    encryption += ("A");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("097")) {
                    encryption += ("a");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("098")) {
                    encryption += ("b");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("099")) {
                    encryption += ("c");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("100")) {
                    encryption += ("d");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("101")) {
                    encryption += ("e");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("102")) {
                    encryption += ("f");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("103")) {
                    encryption += ("g");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("104")) {
                    encryption += ("h");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("105")) {
                    encryption += ("i");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("106")) {
                    encryption += ("j");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("107")) {
                    encryption += ("k");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("108")) {
                    encryption += ("l");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("109")) {
                    encryption += ("m");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("110")) {
                    encryption += ("n");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("111")) {
                    encryption += ("o");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("112")) {
                    encryption += ("p");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("113")) {
                    encryption += ("q");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("114")) {
                    encryption += ("r");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("115")) {
                    encryption += ("s");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("116")) {
                    encryption += ("t");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("117")) {
                    encryption += ("u");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("118")) {
                    encryption += ("v");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("119")) {
                    encryption += ("w");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("120")) {
                    encryption += ("x");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("121")) {
                    encryption += ("y");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("122")) {
                    encryption += ("z");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("123")) {
                    encryption += (".");
                    cnt = 0;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("000")) {
                    encryption += (" ");
                } else if (s.substring(i, i + 3).equalsIgnoreCase("124")) {
                    encryption += ("?");
                    cnt = 0;
                } else if (s.substring(i, i + 3).equalsIgnoreCase("125")) {
                    encryption += ("!");
                    cnt = 0;
                }
                else if (s.substring(i, i + 3).equalsIgnoreCase("126")) {
                    encryption += (",");
                    cnt = 0;
                }
            }
        }
        encryption += ("A".repeat(Math.max(0, numA)));

        return encryption.toString();
    }
    
    public static int numLetterBlocks(String msg) {
        int area = (int)Math.sqrt(Math.sqrt(msg.length())) * (int)Math.sqrt(Math.sqrt(msg.length()));
        if(msg.length() % area != 0) {
            return (msg.length() / area) + 1;
        }
        else {
            return msg.length() / area;
        }
    }
    
    public static int numRowAndCol(String msg) {
    	int a = (int) Math.sqrt(Math.sqrt(msg.length()));
		return a;
    }

    public class CustomTextArea extends JTextArea {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private BufferedImage image;

        public CustomTextArea() {
            super(20, 20);
            try {
            	
            	image = ImageIO.read(RouteCipherGUI.class.getResourceAsStream("/resources/Inable.png"));
            	
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public boolean isOpaque() {
            return false;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            if (image != null) {
                int x = getWidth() - image.getWidth();
                int y = getHeight() - image.getHeight();
                g2d.drawImage(image, x, y, this);    
            }
            super.paintComponent(g2d);
            g2d.dispose();
        }

    }

}

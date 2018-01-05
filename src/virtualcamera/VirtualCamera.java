/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualcamera;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Kuba
 */
public class VirtualCamera implements GLEventListener, ActionListener {

    private final GLU glu = new GLU();
    public static double move_z = -10.0f;
    public static double move_x = 0.0f;
    public static double move_y = 0.0f;
    public static double move_z_change = 0.0f;
    public static double move_y_rotate = 0.0f;
    public static double move_x_rotate = 0.0f;
    public static double move_z_rotate = 0.0f;
    public static double move_y_post = 0.0f;
    public static double move_x_post = 0.0f;
    public static double move_z_post = 0.0f;
    public static double alpha = 0.0f;
    public static double theta = 0.0f;
    public static double phi = 0.0f;
    public static int x = -10;
    public static int oldX; 
    public static int y = -10;
    public static int oldY; 
    
    public static double xS = 1.0f;
    public static double yS = 1.0f;
    public static double zS = 1.0f;
    //public static int oldY; 
    
    public static boolean rotate;
    
    

    @Override
    public void init(GLAutoDrawable drawable) {
        
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        
    }

    @Override
    public void display(GLAutoDrawable drawable) {
       final GL2 gl = drawable.getGL().getGL2();
       /*
       float x = 1.0f;
       float y = 1.0f;
       float z = 1.0f;
       float eyeX = 0.0f;
       float eyeY = 0.0f;
       float eyeZ = 5.0f;
       float pickObjX = 0.0f;
       float pickObjY = 1.0f;
       float pickObjZ = 0.0f;
       */
       
       
       gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
       gl.glLoadIdentity(); // Reset The View
       
       move_y_rotate = move_y;
       move_x_rotate = move_x;
       move_z_rotate = move_z;
       
      
       
       //move_x_post = Math.tan(Math.abs(theta))/move_z;
       //move_y_post = move_y/cos(Math.abs(phi));
       
       if(theta > 0) {
    	   move_x_post = (Math.sin(Math.abs(theta))*Math.abs(move_z_change));
       }
       else {
    	   move_x_post = -(Math.sin(Math.abs(theta))*Math.abs(move_z_change));
       }
       
       move_z_post = -((Math.cos(Math.abs(theta))*Math.abs(move_z_change)));
       
       
       
       
       System.out.println("Move x is: " + move_x + " and Move X Post is: " + move_x_post);
       System.out.println("Move z is: " + move_z + " and Move Z Post is: " + move_z_post);
       System.out.println("Theta is " + theta);
       
       
       
       
       
       //gl.glTranslated( move_x, move_y,   move_z ); // Move the shape
       
       
       //TODO translatef manage it!
       gl.glTranslated( -move_x_rotate, -move_y_rotate,   -move_z_rotate ); // Move the shape
       double temp_angle_y =  Math.atan((Math.sqrt(move_y_rotate*move_y_rotate+move_z_rotate*move_z_rotate)/move_x_rotate));
       double temp_angle_x = Math.atan(move_y_rotate/move_z_rotate);
       gl.glRotated(-temp_angle_x, 1.0f, 0, 0);
       gl.glRotated(-temp_angle_y, 0, 1.0f, 0);
       gl.glRotated(-theta, 0, 1.0f, 0);
       gl.glRotated(-phi, 1.0f, 0, 0);
       gl.glRotated(-alpha, 0, 0, 1.0f);
       gl.glRotated(temp_angle_y, 0, 1.0f, 0);
       gl.glRotated(temp_angle_x, 1.0f, 0, 0);
       gl.glTranslated( move_x_rotate, move_y_rotate,   move_z_rotate );
       
       if(theta!=0 && rotate == false) {
    	   gl.glTranslated( move_x_post, move_y,   move_z_post ); // Move the shape
       }
       else {
    	   gl.glTranslated( move_x, move_y,   move_z ); // Move the shape
       }
       
       
       gl.glScaled(xS, yS, zS);
       ShapeDrawer sp = new ShapeDrawer();
       
       sp.display(drawable);
       
       
       gl.glEnd();
       gl.glFlush();
       
       //rtt += 0.01f;
       
            
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
       GL2 gl = drawable.getGL().getGL2();
       
       if(height <=0)
           height = 1;
       final float h = ( float ) width/ ( float ) height;
       gl.glViewport(0, 0, width, height );
       gl.glMatrixMode( GL2.GL_PROJECTION );
       gl.glLoadIdentity();
		
       glu.gluPerspective( 45.0f, h, 1.0f, 300.0f );
       gl.glMatrixMode( GL2.GL_MODELVIEW );
       float[] anarrayp;
       anarrayp = new float[1000];
       //gl.glGetFloatv(GL2.GL_MODELVIEW, anarrayp);
       gl.glLoadIdentity();
       
    }
    
    public static void main(String[] args) {
           //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get( GLProfile.GL2 );
      GLCapabilities capabilities = new GLCapabilities(profile);
          
      // The canvas
      final GLCanvas glcanvas = new GLCanvas( capabilities );
      VirtualCamera vc = new VirtualCamera();
      glcanvas.addGLEventListener( vc );
      glcanvas.setSize( 1000, 750  );
      
      
      
      //creating fram
      final JFrame frame = new JFrame("3d graphics)");
      frame.setSize(1200,800);
      final JPanel mainpanel = new JPanel (new BorderLayout());
      final JPanel buttonpanel = new JPanel();
      final JPanel canvaspanel = new JPanel();
      
      JButton FWDbutton = new JButton("Move forward");
      JButton BCKbutton = new JButton("Move backward");
      JButton LEFTbutton = new JButton("Move left");
      JButton RIGHTbutton = new JButton("Move right");
      JButton UPbutton = new JButton("Move Down");
      JButton Downbutton = new JButton("Move up");
      JButton ROTATERIGHTbutton = new JButton("rotate right");
      JButton ROTATELEFTbutton = new JButton("rotate left");
      JButton ROTATEUPbutton = new JButton("rotate up");
      JButton ROTATEDOWNbutton = new JButton("rotate down");
      JButton TILTRIGHTbutton = new JButton("Tilt right");
      JButton TILTLEFTbutton = new JButton("Tilt left");
      JButton ZOOMbutton = new JButton("ZOOM");
      JButton ZOOMOUTbutton = new JButton("ZOOM");
      

      FWDbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_z_change+= 2.0f;
          }
      });
      
      BCKbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_z_change-= 2.0f;
          }
      });
      
      RIGHTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_x-= 0.5f;
          }
      });
      
      LEFTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_x+= 0.5f;
          }
      });
      
      UPbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_y+= 0.5f;
          }
      });
      
      Downbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              move_y-= 0.5f;
          }
      });
      
      ROTATELEFTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              /*
        	  oldX = x;
              x+=5;
              theta += (x-oldX)*1.0f;
              */
        	  /*
              gl.glTranslatef(move_x, move_y, move_z);
              glRotatef(yr*20+moveYr*20, 0.0f, 1.0f, 0.0f);
              glRotatef(zr*20+moveZr*20, 1.0f, 0.0f, 0.0f);
              glTranslatef(0.0f, 0, zoom); // translate back from origin
              
          		*/
        	  rotate = true;
        	  theta+=1;
          }
      });
      
      ROTATERIGHTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = true;
        	  theta-=1;
          }
      });
      
       ROTATEUPbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = true;
              oldY = y;
              y+=5;
              phi += 1;
          }
      });
      
      ROTATEDOWNbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = true;
              oldY = y;
              y+=5;
              phi -= 1;
          }
      });
      
       TILTLEFTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              alpha+=1.0f;
          }
      });
      
      TILTRIGHTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              alpha-=1.0f;
          }
      });
      
      ZOOMbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              xS+=1.0f;
              yS+=1.0f;
              zS+=1.0f;
          }
      });
      
      ZOOMOUTbutton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  rotate = false;
              xS-=1.0f;
              yS-=1.0f;
              zS-=1.0f;
          }
      });
      
      
      
      buttonpanel.add(FWDbutton);
      buttonpanel.add(BCKbutton);
      buttonpanel.add(LEFTbutton);
      buttonpanel.add(RIGHTbutton);
      buttonpanel.add(UPbutton);
      buttonpanel.add(Downbutton);
      buttonpanel.add(ROTATELEFTbutton);
      buttonpanel.add(ROTATERIGHTbutton);
      buttonpanel.add(ROTATEUPbutton);
      buttonpanel.add(ROTATEDOWNbutton);
      buttonpanel.add(TILTLEFTbutton);
      buttonpanel.add(TILTRIGHTbutton);
      buttonpanel.add(ZOOMbutton);
      buttonpanel.add(ZOOMOUTbutton);
      
      mainpanel.add(buttonpanel, BorderLayout.NORTH);
      canvaspanel.add(glcanvas);
      mainpanel.add(canvaspanel, BorderLayout.SOUTH);
      
      frame.getContentPane().add(mainpanel);

      frame.setVisible( true );
      
      final FPSAnimator animator = new FPSAnimator(glcanvas,300,true);
      animator.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

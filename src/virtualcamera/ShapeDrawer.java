/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualcamera;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Kuba
 */
public class ShapeDrawer implements GLEventListener, ActionListener{

    @Override
    public void init(GLAutoDrawable drawable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display(GLAutoDrawable drawable) {
         final GL2 gl = drawable.getGL().getGL2();
         
       
       float x = 1.0f;
       float y = 1.0f;
       float z = 1.0f;
       
       //Floor start
        gl.glBegin(GL2.GL_LINE_LOOP); 
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( -5*x,-y,-5*z );
        gl.glVertex3f( 5*x,-y,-5*z );
        gl.glVertex3f( 5*x,-y,5*z );
        gl.glVertex3f( -5*x,-y,5*z );
        gl.glEnd();
       //Floor end
       
       //LEft wall start
        gl.glBegin(GL2.GL_LINE_LOOP); 
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( -5*x,-y,-5*z );
        gl.glVertex3f( -5*x,5*y,-5*z );
        gl.glVertex3f( -5*x,5*y,5*z );
        gl.glVertex3f( -5*x,-y,5*z );
        gl.glEnd();
       //Left wall end
        
       //Right wall start
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 5*x,-y,-5*z );
        gl.glVertex3f( 5*x,5*y,-5*z );
        gl.glVertex3f( 5*x,5*y,5*z );
        gl.glVertex3f( 5*x,-y,5*z );
        gl.glEnd();
       //Right wall end
        
       //Back wall start
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( -5*x,5*y,-5*z );
        gl.glVertex3f( 5*x,5*y,-5*z );
        gl.glEnd();
       //Back wall end
       
       
       
       //Cube 1  start ------------------------------
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( -x,-y,0 );
        gl.glVertex3f( -x,y,0 );
        gl.glVertex3f( x,y,0 );
        gl.glVertex3f( x,-y,0 );
        gl.glEnd();
       
        //draw second square
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glColor3f( 1.0f, 1.0f, 0.0f ); // yellow
        gl.glVertex3f( -x,-y,z );
        gl.glVertex3f( -x,y,z );
        gl.glVertex3f( x,y,z );
        gl.glVertex3f( x,-y,z );
        gl.glEnd();
       
        //draw connectors
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f( -x,y,z );
        gl.glVertex3f( -x,y,0 );
        gl.glVertex3f( x,y,z );
        gl.glVertex3f( x,y,0 );
        gl.glEnd();
       
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f( x,-y,z );
        gl.glVertex3f( x,-y,0 );
        gl.glVertex3f( -x,-y,z );
        gl.glVertex3f( -x,-y,0 );
        gl.glEnd();
       //Cube 1 end -------------------------------
        
        //      Cube 2 start ----------------------
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 2*x,-y,0 );
        gl.glVertex3f( 2*x,y,0 );
        gl.glVertex3f((float) (2.5*x),y,0 );
        gl.glVertex3f((float) (2.5*x),-y,0 );
        gl.glEnd();
       
        //draw second square
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glColor3f( 1.0f, 1.0f, 0.0f ); // yellow
        gl.glVertex3f( 2*x,-y,z );
        gl.glVertex3f( 2*x,y,z );
        gl.glVertex3f((float) (2.5*x),y,z );
        gl.glVertex3f((float) (2.5*x),-y,z );
        gl.glEnd();
       
        //draw connectors
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f( 2*x,y,z );
        gl.glVertex3f( 2*x,y,0 );
        gl.glVertex3f((float) (2.5*x),y,z );
        gl.glVertex3f((float) (2.5*x),y,0 );
        gl.glEnd();
       
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f((float) (2.5*x),-y,z );
        gl.glVertex3f((float) (2.5*x),-y,0 );
        gl.glVertex3f( 2*x,-y,z );
        gl.glVertex3f( 2*x,-y,0 );
       //Cube 2 end ---------------------------------
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

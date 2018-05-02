import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Bullet {
    
    private Image bombImg;
    private BufferedImage bombBuffImage;
    
    private int xPosi;
    private int yPosi;
    private int direction;
    public boolean stop=false;
    private float velocityX=0.05f,velocityY=0.05f;
    
    public Bullet(int x,int y,int direction) {
        xPosi=x;
        yPosi=y;
        this.direction=direction;
        stop=false;
        bombImg=new ImageIcon("Images/bomb.png").getImage();
        
        bombBuffImage=new BufferedImage(bombImg.getWidth(null),bombImg.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        bombBuffImage.createGraphics().drawImage(bombImg,0,0,null);
        Thread t= new Thread(new Runnable() {
        public void run() {

        }
    }); 
    t.start();
    }
    public int getPosiX() {
        return xPosi;
    }
    public int getPosiY() {
        return yPosi;
    }
    public void setPosiX(int x) {
        xPosi=x;
    }
    public void setPosiY(int y) {
        yPosi=y;
    }
    public BufferedImage getBomBufferdImg() {
        return bombBuffImage;
    }

    
    public boolean checkCollision() 
    {
        ArrayList<Tank>clientTanks=GameField.getClients();
        int x,y;
        for(int i=1;i<clientTanks.size();i++) {
            if(clientTanks.get(i)!=null) {
                x=clientTanks.get(i).getXposition();
                y=clientTanks.get(i).getYposition();
                
                if((yPosi>=y&&yPosi<=y+43)&&(xPosi>=x&&xPosi<=x+43)) 
                {
                    
                    ClientGUI.setScore(50);
                    
                    ClientGUI.gameStatusPanel.repaint();
                    
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    if(clientTanks.get(i)!=null)
                     Client.getGameClient().sendToServer(new MessageHandler().RemoveClientPacket(clientTanks.get(i).getTankID()));
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    public void startBombThread(boolean chekCollision) {
        
            new BombShotThread(chekCollision).start();
            
    }
    
    private class BombShotThread extends Thread 
    {    
        boolean checkCollis;
        public BombShotThread(boolean chCollision)
        {
            checkCollis=chCollision;
        }
        public void run() 
        {
            if(checkCollis) {
                
                if(direction==1) 
                {
                    xPosi=17+xPosi;
                    while(yPosi>50) 
                    {
                        yPosi=(int)(yPosi-yPosi*velocityY);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                    
                } 
                else if(direction==2) 
                {
                    yPosi=17+yPosi;
                    xPosi+=30;
                    while(xPosi<564) 
                    {
                        xPosi=(int)(xPosi+xPosi*velocityX);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==3) 
                {
                    yPosi+=30;
                    xPosi+=20;
                    while(yPosi<505) 
                    {    
                        yPosi=(int)(yPosi+yPosi*velocityY);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==4) 
                {
                    yPosi=21+yPosi;
                    
                    while(xPosi>70) 
                    {
                        xPosi=(int)(xPosi-xPosi*velocityX);
                        if(checkCollision()) 
                        {
                            break;
                        }
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                
                stop=true;
            } 
            else 
            {
                 if(direction==1) 
                {
                    xPosi=17+xPosi;
                    while(yPosi>50) 
                    {
                        yPosi=(int)(yPosi-yPosi*velocityY);
                        
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                    
                } 
                else if(direction==2) 
                {
                    yPosi=17+yPosi;
                    xPosi+=30;
                    while(xPosi<564) 
                    {
                        xPosi=(int)(xPosi+xPosi*velocityX);
                        
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==3) 
                {
                    yPosi+=30;
                    xPosi+=20;
                    while(yPosi<505) 
                    {    
                        yPosi=(int)(yPosi+yPosi*velocityY);
                        
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                else if(direction==4) 
                {
                    yPosi=21+yPosi;
                    
                    while(xPosi>70) 
                    {
                        xPosi=(int)(xPosi-xPosi*velocityX);
                        
                        try {
                            
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                }
                
                stop=true;
            }
        }
    }
}

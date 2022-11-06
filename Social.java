import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class Social{
    public static void main(String[] args) {
        new Canvas();
    }
}
class Canvas extends JFrame{
    
    public Canvas(){
        setSize(1920,1000);//set size of the frame
        setTitle("Social Distance");//set title of the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);//set the default close operation
        setLayout(new BorderLayout());//set the layout of the frame
        setLocationRelativeTo(null);//set the location of the frame
        add(new SocialArea());//add the panel to the frame


        setVisible(true);//set the frame visible
    }
}

class SocialArea extends JPanel implements ComponentListener{




    class MySlider extends JPanel{
        boolean is1FPS=false;
        public MySlider(){
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            setOpaque(false);
            JSlider slider = new JSlider(JSlider.HORIZONTAL); //create a slider
            slider.setOpaque(false);//set the slider to be transparent
            slider.setMajorTickSpacing(25);//set the major tick spacing
            slider.setMinorTickSpacing(5);//set the minor tick spacing
            slider.setPaintTicks(true);//set the paint ticks to true
            slider.setValue(40);//set the value of the slider
            slider.setPaintTicks(true);//set the paint ticks to true
            slider.addChangeListener(new ChangeListener() {//add a change listener to the slider
                public void stateChanged(ChangeEvent e) {//when the slider value is changed
                    if(!is1FPS){//if the slider is not at 1fps
                        threadSpeed=11-slider.getValue()/10;//set the thread speed
                        for(Hamster hamster: list){//for each hamster
                            hamster.setCounter2(slider.getValue()+25);//set the counter2 to the value of the slider plus 25
                        }
                    }
                    
                }
            });
            gbc.gridx=gbc.gridy = 0;
            gbc.weightx=0;
            gbc.insets = new Insets(0,20,0,20);
            gbc.anchor = GridBagConstraints.CENTER;
            add(slider,gbc);
            JButton button = new JButton("  Low FPS   ");//create a button
            button.setFocusable(false);//set the button to be not focusable
            button.setBackground(Color.WHITE);//set the background color of the button
            button.setOpaque(false);//set the button to be transparent
            button.addActionListener(new ActionListener(){//add an action listener to the button
                @Override
                public void actionPerformed(ActionEvent e) {//when the button is pressed
                    if(threadSpeed==100){//if the thread speed is 100
                        threadSpeed=11-slider.getValue()/10;//set the thread speed to the value of the slider divided by 10
                        button.setForeground(Color.BLACK);//set the foreground color of the button to black
                        button.setBackground(Color.WHITE);//set the background color of the button to white
                        is1FPS=false;//set the is1FPS to false
                        button.setText("  Low FPS   ");//set the text of the button to "Low FPS"
                        for(Hamster hamster: list){//for each hamster
                            hamster.setCounter2(slider.getValue()+25);//set the counter2 to the value of the slider plus 25
                        }//end for each
                        slider.setEnabled(true);//enable the slider
                    }else{
                        slider.setEnabled(false);//disable the slider
                        is1FPS=true;//set the is1FPS to true
                        threadSpeed=100;//set the thread speed to 100
                        for(Hamster hamster: list){//for each hamster
                            hamster.setCounter2(10);//set the counter2 to 10
                        }
                        button.setForeground(Color.RED);//set the foreground color of the button to red
                        button.setBackground(Color.WHITE);//set the background color of the button to white
                        button.setText("Slider Value");
                    }
                }});
            gbc.gridx=1;
            add(button, gbc);
        }
    }
    class BarChart extends JPanel{
        public BarChart() {
            setOpaque(false);
        }
        private Map<Color, Integer> bars =
                new LinkedHashMap<Color, Integer>();
        
        /**
         * Add new bar to chart
         * @param color color to display bar
         * @param value size of bar
         */
        public void addBar(Color color, int value)
        {
            bars.put(color, value);
        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
            // determine longest bar
            int max = Integer.MIN_VALUE;//set the max to the minimum value of an integer
            for (Integer value : bars.values()){//for each value in the bars
                max = Math.max(max, value);//set the max to the max of the max and the value
            }
            
            // paint bars
            
            int temp = (getWidth() / bars.size()) - 2;//set the temp to the width of the bar chart divided by the size of the bars minus 2
            int x = 1;//set the x to 1
            for (Color color : bars.keySet())
            {
                int value = bars.get(color);//set the value to the value of the color
                int temp2 = (int) 
                                ((getHeight()-5) * ((double)value / max));
                g.setColor(color);
                g.fillRect(x, getHeight() - temp2, temp, temp2);//draw the rectangle
                g.setColor(Color.black);//set the color to black
                g.drawRect(x, getHeight() - temp2, temp, temp2);//draw the rectangle
                x += (temp + 2);//add the temp plus 2 to x
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(bars.size() * 50 + 2, 50);
        }

	
    }
    class Labels extends JPanel{
		ArrayList<String> labels = new ArrayList<String>();
        JLabel label1=new JLabel(),label2=new JLabel(),label3=new JLabel();
		public Labels(){
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            labels.add("Healthy:"+400);//add the healthy label
            labels.add("Patient:  "+1);//add the patient label
            labels.add("Death:    "+0);//add the death label
            
            int temp =0;
            for(String str: labels){//for each string in the labels
                if(temp==0){
                    label1.setText(str);
                    label1.setOpaque(false);
                    label1.setFont(new Font("Arial",Font.BOLD,14));
                    label1.setBackground(Color.WHITE);
                    add(label1,gbc);
                    gbc.gridx+=1;
                }else if(temp==1){
                    label2.setText(str);
                    label2.setOpaque(false);
                    label2.setFont(new Font("Arial",Font.BOLD,14));
                    label2.setBackground(Color.WHITE);
                    add(label2,gbc);
                    gbc.gridx+=1;
                }else{
                    label3.setText(str);
                    label3.setOpaque(false);
                    label3.setFont(new Font("Arial",Font.BOLD,14));
                    label3.setBackground(Color.WHITE);
                    add(label3,gbc);
                    gbc.gridx+=1;
                }
                
                temp++;
            }
        }
		public void addLabel(String str)
		{
			labels.add(str);
		}
        public void setLabel( int asdf,String str){
            if(str.indexOf("Healthy:")>-1){
                String str2 = str.substring(str.indexOf(":")+1);
                if(str2.length()==1){
                    str2="  "+str2;
                }else if(str2.length()==2){
                    str2=" "+str2;
                }else if(str2.length()==3){
                

                }
                str = "Healthy:"+str2;
            }
            if(str.indexOf("Patient:")>-1){
                String str2 = str.substring(str.indexOf(":")+3);
                if(str2.length()==1){
                    str2="  "+str2;
                }else if(str2.length()==2){
                    str2=" "+str2;
                }else if(str2.length()==3){
                

                }
                str = "Patient:  "+str2;
            }
            if(str.indexOf("Death:")>-1){
                String str2 = str.substring(str.indexOf(":")+5);
                if(str2.length()==1){
                    str2="  "+str2;
                }else if(str2.length()==2){
                    str2=" "+str2;
                }else if(str2.length()==3){
                

                }
                str = "Death:    "+str2;
            }
            if(asdf==1){
                label1.setText(str);
            }else if(asdf==2){
                label2.setText(str);

            }else{
                label3.setText(str);

            }
        }
    }






    int width,height;
    ArrayList<Hamster> list = new ArrayList<Hamster>();
    BarChart chart;
    Labels labels = new Labels();
    public SocialArea(){
        addComponentListener(this);//add the component listener
        setBackground(Color.WHITE);//set the background color to white
        setLayout(new GridBagLayout());//set the layout to gridbaglayout
        JPanel panel = new JPanel();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy =0;
        gbc.weightx = gbc.weighty =1;
        gbc.anchor= GridBagConstraints.NORTH;
        gbc.weightx =1;
        
        add(new MySlider(),gbc);

        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        chart = new BarChart();
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        
        panel.add(chart,gbc);
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(6,6,6,6);
        gbc.gridy=1;
        gbc.anchor = GridBagConstraints.SOUTH;

        add(panel,gbc);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        
        labels.addLabel("Healthy:"+400);
        labels.addLabel("Patient:  "+1);
        labels.addLabel("Death:    "+0);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0;
        
        panel.add(labels,gbc);
        gbc.weightx = 1;
        gbc.gridy=2;
        gbc.insets = new Insets(0,0,8,0);
        gbc.anchor = GridBagConstraints.SOUTH;
        add(panel,gbc);
    }
    int deathCount = -1, liveCount = -1, sickCount = -1;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//call the super class paintComponent
        Graphics2D g2 = (Graphics2D)g;//cast the graphics to Graphics2D
        g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GREEN);//set the color to green
        
        
        for(Hamster hamster:list){//for each hamster in the list
            
            g2.setColor(getColor(hamster.getColor()));//set the color to the hamster's color
            g2.fillOval(hamster.getPoint().x,hamster.getPoint().y,10,10);//draw the hamster
        }
        labels.setLabel(1,"Healthy:"+((liveCount==-1)?0:liveCount));
        labels.setLabel(2,"Patient:  "+((sickCount==-1)?0:sickCount));
        labels.setLabel(3,"Death:    "+((deathCount==-1)?0:deathCount));
        if(deathCount!=-1){
            

            chart.addBar(Color.RED, deathCount);//add the red bar
            //labels.setLabel(3,"Death:    "+((deathCount==-1)?0:deathCount));
            //chart.repaint();

        }
        if(sickCount!=-1){
            chart.addBar(Color.MAGENTA, sickCount);//add the magenta bar
            //labels.setLabel(2,"Patient:  "+((sickCount==-1)?0:sickCount));

            //chart.repaint();
            

        }
        if(liveCount!=-1){
            //labels.setLabel(1,"Healthy:"+((liveCount==-1)?0:liveCount));

            chart.addBar(Color.GREEN, liveCount);//add the green bar
            //chart.repaint();
            

        }
        
    }
    
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public double getRandomNumber(double min, double max) {
        return  ((Math.random() * (max - min)) + min);
    }
    public Color getColor(int c){
        if(c==1) return Color.GREEN;
        else if(c==2) return Color.MAGENTA;
        else return Color.RED;
    }
    
    Thread t;
    boolean firstTime=true;
    @Override
    public void componentResized(ComponentEvent e) {
        
        width=getSize().width;
        height=getSize().height;
        setVisible(false);
        setVisible(true);

        
        
        
    }
    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    double downLim=-1,upLim=1,defaultSpeed=40*0.01;
    int threadSpeed=6;
    @Override
    public void componentShown(ComponentEvent e) {
        if(firstTime){//if this is the first time the component is shown
            
            for(int i =0;i<400;i++){//for 400 times
                Hamster newHamster = new Hamster();//create a new hamster
                int x =  getRandomNumber(0,width-10);//get a random x coordinate

                int y =  getRandomNumber(0,height-10);//get a random y coordinate
                newHamster.setPoint(new Point(x,y));//set the hamster's point to the new point
                newHamster.setStatus(1);//set the hamster's status to healthy
                newHamster.setColor(1);//set the hamster's color to green
                newHamster.setPosX(x);
                newHamster.setPosY(y);
                Rectangle rect = new Rectangle(x, y,10,10);//create a new rectangle
                newHamster.setRect(rect);//set the hamster's rectangle to the new rectangle
                double velX = getRandomNumber(downLim,upLim);//get a random x velocity
                double velY =  getRandomNumber(downLim,upLim);//get a random y velocity
                while(velX==0){//while the x velocity is 0
                    velX =  getRandomNumber(downLim,upLim);//get a random x velocity
                }
                while(velY==0){
                    velY =  getRandomNumber(downLim,upLim);
                }
                newHamster.setVelocityX(velX);
                newHamster.setVelocityY(velY);
                list.add(newHamster);//add the hamster to the list
            }
            for(int asd = 0;asd<5;asd++){//for 5 times
                Hamster newHamster = new Hamster();//create a new hamster
                int x =  getRandomNumber(0,width-10);//get a random x coordinate

                int y =  getRandomNumber(0,height-10);//get a random y coordinate
                newHamster.setPoint(new Point(x,y));//set the hamster's point to the new point
                newHamster.setStatus(2);//set the hamster's status to sick
                newHamster.setColor(2);//set the hamster's color to magenta
                newHamster.setPosX(x);//set the hamster's x position to the new x position
                newHamster.setPosY(y);//set the hamster's y position to the new y position
                Rectangle rect = new Rectangle(x, y,10,10);//create a new rectangle
                newHamster.setRect(rect);//set the hamster's rectangle to the new rectangle
                double velX = getRandomNumber(downLim,upLim);//get a random x velocity
                double velY =  getRandomNumber(downLim,upLim);//get a random y velocity
                while(velX==0){
                    velX =  getRandomNumber(downLim,upLim);
                }
                while(velY==0){
                    velY =  getRandomNumber(downLim,upLim);
                }
                newHamster.setVelocityX(velX);//set the hamster's x velocity to the new x velocity
                newHamster.setVelocityY(velY);//set the hamster's y velocity to the new y velocity
                list.add(newHamster);//add the hamster to the list
            }
            
            firstTime = false;
        }else{
            t.stop();//stop the thread
        }
         t = new Thread(new Runnable() {//create a new thread
            public void run() {
               
              while (true) {

                
                
                deathCount = liveCount = sickCount=0;//set the death count, live count, and sick count to 0
                for(Hamster hamster:list){//for each hamster in the list
                    if(hamster.getStatus()==3){//if the hamster is dead
                        deathCount++;//increment the death count
                    }else if(hamster.getStatus()==1){//if the hamster is healthy
                        liveCount++;//increment the live count
                        for(Hamster hamster2:list){//for each hamster in the list
                            if(hamster!=hamster2 && hamster2.getStatus()==2){//if the hamster is not the same as the other hamster and the other hamster is sick
                                hamster.controlIntersects(hamster2);//control the intersection between the two hamsters
                                if(hamster.getStatus()==2){//if the hamster is now sick
                                    liveCount--;//decrement the live count
                                    sickCount++;   //increment the sick count
                                    break; 
                                }
                            }
                        }
                    }else if(hamster.getStatus()==2){//if the hamster is sick
                        hamster.updateStatus();//update the hamster's status
                        if(hamster.getStatus()==1){//if the hamster is now healthy
                            liveCount++;//increment the live count
                        }else if(hamster.getStatus()==3){//if the hamster is now dead
                            deathCount++;//increment the death count
                        }else{
                            sickCount++;//increment the sick count
                        }
                    }
                    
                    
                   if(hamster.getStatus()!=3){ //if the hamster is not dead
                        int newX = (int)(hamster.getPosX()+hamster.getVelocityX());//get the new x position
                        int newY = (int)(hamster.getPosY()+hamster.getVelocityY());//get the new y position
                        
                        if(newX>=width-10||newX<=0){//if the new x position is greater than or equal to the width of the panel minus 10 or less than or equal to 0
                            hamster.setVelocityX(-1*hamster.getVelocityX());//set the hamster's x velocity to the negative of the hamster's x velocity
                            newX = (int)(hamster.getPosX()+hamster.getVelocityX());//get the new x position
                        }
                        if(newY>=height-10||newY<=0){//if the new y position is greater than or equal to the height of the panel minus 10 or less than or equal to 0
                            hamster.setVelocityY(-1*hamster.getVelocityY());//set the hamster's y velocity to the negative of the hamster's y velocity
                            newY = (int)(hamster.getPosY()+hamster.getVelocityY());//get the new y position
                        }
                        if(hamster.getVelocityX()<0 && defaultSpeed>0) defaultSpeed = -defaultSpeed;//if the hamster's x velocity is less than 0 and the default speed is greater than 0, set the default speed to the negative of the default speed
                        if(hamster.getVelocityX()>0 && defaultSpeed<0) defaultSpeed = -defaultSpeed;//if the hamster's x velocity is greater than 0 and the default speed is less than 0, set the default speed to the negative of the default speed
                        hamster.setPosX(hamster.getPosX()+hamster.getVelocityX()+defaultSpeed);//set the hamster's x position to the new x position
                        if(hamster.getVelocityY()<0 && defaultSpeed>0) defaultSpeed = -defaultSpeed; //if the hamster's y velocity is less than 0 and the default speed is greater than 0, set the default speed to the negative of the default speed
                        if(hamster.getVelocityY()>0 && defaultSpeed<0) defaultSpeed = -defaultSpeed; //if the hamster's y velocity is greater than 0 and the default speed is less than 0, set the default speed to the negative of the default speed
                        hamster.setPosY(hamster.getPosY()+hamster.getVelocityY()+defaultSpeed);//set the hamster's y position to the new y position
                        Rectangle rect = new Rectangle(newX, newY,10,10);//create a new rectangle
                        hamster.setRect(rect);//set the hamster's rectangle to the new rectangle
                        hamster.setPoint(new Point(newX,newY));//set the hamster's point to the new point
                    }
                }
                if(deathCount==0 ||liveCount ==0|| sickCount==0){//if the death count is 0 or the live count is 0 or the sick count is 0
                    if(deathCount==0){//if the death count is 0
                        deathCount--;//decrement the death count
                    }
                    if(liveCount==0){//if the live count is 0
                        liveCount--;//decrement the live count
                    }
                    if(sickCount==0){//if the sick count is 0
                        sickCount--;//decrement the sick count
                    }
                }
                
                repaint();//repaint the panel
                try {
                  Thread.sleep(threadSpeed);//sleep the thread for the thread speed
                } catch (InterruptedException e) {
                  e.printStackTrace();
              }}

              
          }});
          t.setDaemon(true);  //set the thread to a daemon thread
          t.start();    //start the thread
        
    }
    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}

class Hamster{



    
    private Rectangle rect;
    private int status=0,color = 1,counter=getRandomNumber(-16000,0),counter2=1;
    private Point point;
    private double posX, posY;
    private double velocityX,velocityY;
    
    public void setCounter2(int x){
        this.counter2 = x;
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public void controlIntersects(Hamster hamster2){//control the intersection between two hamsters
        if(this.rect.intersects(hamster2.rect)){//if the two hamsters intersect
            int x = getRandomNumber(1,101);//get a random number between 1 and 100
            if(x>95){//if the random number is greater than 95
                
                this.setStatus(2);//set the status of the first hamster to sick
                this.color = 2;//set the color of the first hamster to red
            }
        }
    }
    public void updateStatus(){//update the status of the hamster
        if(counter < 30000){//if the counter is less than 30000
           
            counter+=getRandomNumber(1,counter2);//increment the counter by a random number between 1 and the counter2
        }else{
            if(getRandomNumber(1,101)>=90){//if the random number is greater than or equal to 90
                counter =getRandomNumber(-16000,0);//set the counter to a random number between -16000 and 0
                this.status =1;//set the status to 1
                this.color = 1;//set the color to green
                return;
            }
                this.status =3;//set the status to 3
                this.color = 3;//set the color to black
        }
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getVelocityX() {
        return this.velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    
}
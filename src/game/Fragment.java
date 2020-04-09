package game;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Fragment {
    private BufferedImageLoader imgUpload;
    
    //  PLAYER
    private SpriteSheet LPlayerSS;
    private SpriteSheet RPlayerSS;
    //  JACKET
    private SpriteSheet LJacketSS;
    private SpriteSheet RJacketSS;
    //  WOLF
    private SpriteSheet LWolfEnemySS;
    private SpriteSheet RWolfEnemySS;
    private SpriteSheet LWolfRunSS;
    private SpriteSheet RWolfRunSS;
   
    private SpriteSheet LSnakeSS;
    private SpriteSheet RSnakeSS;

    //  COIN, FIRE, DOOR
    private SpriteSheet CoinSS;
    private SpriteSheet FireSS;
    private SpriteSheet GateSS;

    private BufferedImage ice = null;
    private BufferedImage block0 = null;
    private BufferedImage block1 = null;
    private BufferedImage block2 = null;
    private BufferedImage block3 = null;
    private BufferedImage block4 = null;

    private BufferedImage LPlayerBufferedImage = null;
    private BufferedImage RPlayerBufferedImage = null;

    private BufferedImage LJacketBufferedImage = null;
    private BufferedImage RJacketBufferedImage = null;

    private BufferedImage LWolfEnemyBufferedImage = null;
    private BufferedImage RWolfEnemyBufferedImage = null;
    private BufferedImage LWolfRunBufferedImage = null;

    private BufferedImage snakeBufferedImage = null;
    //  COIN, FIRE, DOOR
    private BufferedImage CoinBufferedImage = null;
    private BufferedImage FireBufferedImage = null;
    private BufferedImage Door = null;
    private BufferedImage GateBufferedImage = null;

    private BufferedImage[] block = new BufferedImage[5];

    private BufferedImage[] rightPlayer = new BufferedImage[9];
    private BufferedImage[] leftPlayer = new BufferedImage[9];
    private BufferedImage[] playerJumping = new BufferedImage[2];
    private BufferedImage[] playerFalling = new BufferedImage[2];

    private BufferedImage[] rightJacket = new BufferedImage[9];
    private BufferedImage[] leftJacket = new BufferedImage[9];
    private BufferedImage[] jacketJumping = new BufferedImage[2];
    private BufferedImage[] jacketFalling = new BufferedImage[2];

    private BufferedImage[] enemyWolf = new BufferedImage[22];
    private BufferedImage[] Boss = new BufferedImage[88];
    private BufferedImage[] snake = new BufferedImage[8];
    //  COIN, FIRE, DOOR
    private BufferedImage[] coin = new BufferedImage[6];
    private BufferedImage[] fire = new BufferedImage[4];
    private BufferedImage[] gate = new BufferedImage[4];

    public Fragment() {
        imgUpload = new BufferedImageLoader();
        try {
            ice = imgUpload.loadIamge("/ice3.png");
            block0 = imgUpload.loadIamge("/block.png");
            block1 = imgUpload.loadIamge("/block1.png");
            block2 = imgUpload.loadIamge("/block2.png");
            block3 = imgUpload.loadIamge("/block3.png");
            block4 = imgUpload.loadIamge("/block4.png");

            LPlayerBufferedImage = imgUpload.loadIamge("/leftPlayer.png");
            RPlayerBufferedImage = imgUpload.loadIamge("/rightPlayer.png");

            RJacketBufferedImage = imgUpload.loadIamge("/OnlyJacket.png");
            LJacketBufferedImage = flip(RJacketBufferedImage);

            LWolfEnemyBufferedImage = imgUpload.loadIamge("/leftWolf.png");
            RWolfEnemyBufferedImage = imgUpload.loadIamge("/rightWolf.png");
            LWolfRunBufferedImage = imgUpload.loadIamge("/leftWolfRun.png");

            snakeBufferedImage = imgUpload.loadIamge("/snake.png");
            //  COIN, FIRE, DOOR
            CoinBufferedImage = imgUpload.loadIamge("/coin.png");
            FireBufferedImage = imgUpload.loadIamge("/sprite_fire.png");
            Door = imgUpload.loadIamge("/rockDoor.png");
            GateBufferedImage = imgUpload.loadIamge("/gate.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        LPlayerSS = new SpriteSheet(LPlayerBufferedImage);
        RPlayerSS = new SpriteSheet(RPlayerBufferedImage);

        LJacketSS = new SpriteSheet(LJacketBufferedImage);
        RJacketSS = new SpriteSheet(RJacketBufferedImage);

        LWolfEnemySS = new SpriteSheet(LWolfEnemyBufferedImage);
        RWolfEnemySS = new SpriteSheet(RWolfEnemyBufferedImage);
        LWolfRunSS = new SpriteSheet(LWolfRunBufferedImage);
        RWolfRunSS = new SpriteSheet(flip(LWolfRunBufferedImage));

        LSnakeSS = new SpriteSheet(flip(snakeBufferedImage));
        RSnakeSS = new SpriteSheet(snakeBufferedImage);

        CoinSS = new SpriteSheet(CoinBufferedImage);
        FireSS = new SpriteSheet(FireBufferedImage);
        GateSS = new SpriteSheet(GateBufferedImage);

        getFragments();
    }

    private void getFragments() {

        block[0] = block0;
        block[1] = block1;
        block[2] = block2;
        block[3] = block3;
        block[4] = block4;

        //  PLAYER
        rightPlayer[0] = RPlayerSS.captureImage(1, 1, 62.5, 68);
        leftPlayer[0] = LPlayerSS.captureImage(8, 1, 62.5, 68);
        for (int i = 1; i < rightPlayer.length; i++) {
            rightPlayer[i] = RPlayerSS.captureImage(i, 4, 62.5, 68);
            leftPlayer[i] = LPlayerSS.captureImage((rightPlayer.length - i), 4, 62.5, 68);
        }
        playerJumping[0] = RPlayerSS.captureImage(7, 1, 62.5, 68);
        playerJumping[1] = LPlayerSS.captureImage(1, 1, 62.5, 68);
        playerFalling[0] = RPlayerSS.captureImage(8, 1, 62.5, 68);
        playerFalling[1] = LPlayerSS.captureImage(2, 1, 62.5, 68);

        //  JACKET
        rightJacket[0] = RJacketSS.captureImage(1, 1, 62.5, 68);
        leftJacket[0] = LJacketSS.captureImage(8, 1, 62.5, 68);
        for (int i = 1; i < rightJacket.length; i++) {
            rightJacket[i] = RJacketSS.captureImage(i, 4, 62.5, 68);
            leftJacket[i] = LJacketSS.captureImage((rightJacket.length - i), 4, 62.5, 68);
        }
        jacketJumping[0] = RJacketSS.captureImage(7, 1, 62.5, 68);
        jacketJumping[1] = LJacketSS.captureImage(1, 1, 62.5, 68);
        jacketFalling[0] = RJacketSS.captureImage(8, 1, 62.5, 68);
        jacketFalling[1] = LJacketSS.captureImage(2, 1, 62.5, 68);

        //  WOLF
        enemyWolf[0] = RWolfEnemySS.captureImage(1, 3, 76, 53.5);
        enemyWolf[1] = RWolfEnemySS.captureImage(2, 3, 76, 53.5);
        enemyWolf[2] = RWolfEnemySS.captureImage(3, 3, 76, 53.5);
        enemyWolf[3] = RWolfEnemySS.captureImage(4, 3, 76, 53.5);
        enemyWolf[4] = RWolfEnemySS.captureImage(5, 3, 76, 53.5);
        enemyWolf[5] = RWolfEnemySS.captureImage(6, 3, 76, 53.5);
        enemyWolf[6] = LWolfEnemySS.captureImage(6, 3, 76, 53.5);
        enemyWolf[7] = LWolfEnemySS.captureImage(5, 3, 76, 53.5);
        enemyWolf[8] = LWolfEnemySS.captureImage(4, 3, 76, 53.5);
        enemyWolf[9] = LWolfEnemySS.captureImage(3, 3, 76, 53.5);
        enemyWolf[10] = LWolfEnemySS.captureImage(2, 3, 76, 53.5);
        enemyWolf[11] = LWolfEnemySS.captureImage(1, 3, 76, 53.5);
        enemyWolf[12] = LWolfRunSS.captureImage(1, 1, 78, 43);
        enemyWolf[13] = LWolfRunSS.captureImage(2, 1, 78, 43);
        enemyWolf[14] = LWolfRunSS.captureImage(3, 1, 78, 43);
        enemyWolf[15] = LWolfRunSS.captureImage(4, 1, 78, 43);
        enemyWolf[16] = LWolfRunSS.captureImage(5, 1, 78, 43);
        enemyWolf[17] = RWolfRunSS.captureImage(1, 1, 78, 43);
        enemyWolf[18] = RWolfRunSS.captureImage(2, 1, 78, 43);
        enemyWolf[19] = RWolfRunSS.captureImage(3, 1, 78, 43);
        enemyWolf[20] = RWolfRunSS.captureImage(4, 1, 78, 43);
        enemyWolf[21] = RWolfRunSS.captureImage(5, 1, 78, 43);

        snake[0] = LSnakeSS.captureImage(1, 1, 64, 48);
        snake[1] = LSnakeSS.captureImage(2, 1, 64, 48);
        snake[2] = LSnakeSS.captureImage(3, 1, 64, 48);
        snake[3] = LSnakeSS.captureImage(4, 1, 64, 48);
        snake[4] = RSnakeSS.captureImage(4, 1, 64, 48);
        snake[5] = RSnakeSS.captureImage(3, 1, 64, 48);
        snake[6] = RSnakeSS.captureImage(2, 1, 64, 48);
        snake[7] = RSnakeSS.captureImage(1, 1, 64, 48);
        
        Boss[0]=imgUpload.loadIamge("/DarkSaber/walk/darksaber_walk1.png");
        for (int i = 1; i < 11; i++) {
            Boss[i]=imgUpload.loadIamge("/DarkSaber/walk/darksaber_walk"+(i*4)+".png");
        }
        for (int i = 11; i < 22; i++) {
            Boss[i]=flip(Boss[i-11]);
        }
        for (int i = 22; i < 35; i++) {
            Boss[i]=imgUpload.loadIamge("/DarkSaber/run/darksaber_run"+(i-17)+".png");
        }
        for (int i = 35; i < 48; i++) {
            Boss[i]=flip(Boss[i-13]);
        }
        Boss[48]=imgUpload.loadIamge("/DarkSaber/attack/darksaber_attack1.png");
        for (int i = 49; i < 68; i++) {
            Boss[i]=imgUpload.loadIamge("/DarkSaber/attack/darksaber_attack"+((i-48)*4)+".png");
        }
        
        for (int i = 68; i < 88; i++) {
            Boss[i]=flip(Boss[i-20]);
        }
        //  COIN
        for (int i = 0; i < coin.length; i++) {
            coin[i] = CoinSS.captureImage((i + 1), 1, 25, 25);
        }

        //  FIRE
        for (int i = 0; i < fire.length; i++) {
            fire[i] = FireSS.captureImage(i + 1, 1, 75, 75);
        }

        //   DOOR
        gate[0] = GateSS.captureImage(1, 1, 96, 96);
        gate[1] = GateSS.captureImage(1, 2, 96, 96);
        gate[2] = GateSS.captureImage(1, 3, 96, 96);
        gate[3] = GateSS.captureImage(1, 4, 96, 96);

    }
    

    public BufferedImage[] getBlock() {
        return block;
    }

    public BufferedImage[] getRightPlayer() {
        return rightPlayer;
    }

    public BufferedImage[] getLeftPlayer() {
        return leftPlayer;
    }

    public BufferedImage[] getPlayerJumping() {
        return playerJumping;
    }

    public BufferedImage[] getPlayerFalling() {
        return playerFalling;
    }

    public BufferedImage[] getRightJacket() {
        return rightJacket;
    }

    public BufferedImage[] getLeftJacket() {
        return leftJacket;
    }

    public BufferedImage[] getJacketJumping() {
        return jacketJumping;
    }

    public BufferedImage[] getJacketFalling() {
        return jacketFalling;
    }

    public BufferedImage[] getEnemyWolf() {
        return enemyWolf;
    }

    public BufferedImage[] getCoin() {
        return coin;
    }

    public BufferedImage[] getFire() {
        return fire;
    }

    public BufferedImage[] getGate() {
        return gate;
    }

    public void setGate(BufferedImage[] gate) {
        this.gate = gate;
    }

    public BufferedImage[] getSnake() {
        return snake;
    }

    public BufferedImage getIce() {
        return ice;
    }

    public BufferedImage getDoor() {
        return Door;
    }

    public BufferedImage[] getBoss() {
        return Boss;
    }
    
    
    

    private BufferedImage flip(BufferedImage image) {
//        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
//        tx.translate(0, -image.getHeight(null));
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//        image = op.filter(image, null);

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);

//        tx = AffineTransform.getScaleInstance(-1, -1);
//        tx.translate(-image.getWidth(null), -image.getHeight(null));
//        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//        image = op.filter(image, null);
        return image;
    }

}

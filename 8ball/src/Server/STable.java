package Server;

import java.util.ArrayList;
import java.util.Collections;
import Abstracts.*;
import util.pVector;

public class STable extends AbstractTable {
    final double[][] _startpos = {
            // WRITEME: starting positions for billiard balls
    };

    public STable(double friction_) {
        this.friction = friction_;
        // ball number = -1 : the cue ball
        for (int i = -1; i < 15; i++) {
            this.balls.add(new SBall(i, new pVector(_startpos[i]), this.friction));
        }
    }

    public void step() {
        // this hoop jumping is necessary to check collision between every two balls
        // once without checking duplicate pairs i.e. ab and ba
        ArrayList<AbstractBall> reversed = new ArrayList<AbstractBall>();
        reversed.addAll(balls);
        Collections.reverse(reversed);
        int ballsAtRest = 0;
        for (AbstractBall b1 : this.balls) {
            b1.step();
            reversed.remove(reversed.size() - 1);
            for (AbstractBall b2 : reversed) {
                checkCollision(b1, b2);
            }
            checkEdge(b1);
            // check to see if all balls are at rest
            if (b1.rest)
                ballsAtRest++;
        }
        if (ballsAtRest == balls.size())
            this.rest = true;
    }

    private void checkEdge(AbstractBall ball) {
        // WRITEME: check Edge collisions and score conditions;
    }

    private void checkCollision(AbstractBall b1, AbstractBall b2) {
        // Get distances between the balls components
        pVector distanceVect = pVector.sub(b1.position, b2.position);

        // Calculate magnitude of the vector separating the balls
        double distanceVectMag = distanceVect.mag();

        // Minimum distance before they are touching
        double minDistance = b1.radius + b2.radius;

        if (distanceVectMag < minDistance) {
            double distanceCorrection = (minDistance - distanceVectMag) / 2.0;
            pVector d = distanceVect.copy();
            pVector correctionVector = pVector.mult(d.normalize(),distanceCorrection);
            b2.position = pVector.add(b2.position, correctionVector);
            b1.position = pVector.sub(b1.position,correctionVector);

            // get angle of distanceVect
            double theta = distanceVect.heading();
            // precalculate trig values
            double sine = Math.sin(theta);
            double cosine = Math.cos(theta);

            /*
             * bTemp will hold rotated ball positions. You just need to worry about bTemp[1]
             * position
             */
            pVector[] bTemp = { new pVector(), new pVector() };

            /*
             * this ball's position is relative to the b2 so you can use the vector
             * between them (bVect) as the reference point in the rotation expressions.
             * bTemp[0].position.x and bTemp[0].position.y will initialize automatically to
             * 0.0, which is what you want since b[1] will rotate around b[0]
             */
            bTemp[1].x = cosine * distanceVect.x + sine * distanceVect.y;
            bTemp[1].y = cosine * distanceVect.y - sine * distanceVect.x;

            // rotate Temporary velocities
            pVector[] vTemp = { new pVector(), new pVector() };

            vTemp[0].x = cosine * b1.velocity.x + sine * b1.velocity.y;
            vTemp[0].y = cosine * b1.velocity.y - sine * b1.velocity.x;
            vTemp[1].x = cosine * b2.velocity.x + sine * b2.velocity.y;
            vTemp[1].y = cosine * b2.velocity.y - sine * b2.velocity.x;

            /*
             * Now that velocities are rotated, you can use 1D conservation of momentum
             * equations to calculate the final velocity along the x-axis.
             */
            pVector[] vFinal = { new pVector(), new pVector() };

            // final rotated velocity for b[0]
            vFinal[0].x = ((b1.mass - b2.mass) * vTemp[0].x + 2 * b2.mass * vTemp[1].x) / (b1.mass + b2.mass);
            vFinal[0].y = vTemp[0].y;

            // final rotated velocity for b[0]
            vFinal[1].x = ((b2.mass - b1.mass) * vTemp[1].x + 2 * b1.mass * vTemp[0].x) / (b1.mass + b2.mass);
            vFinal[1].y = vTemp[1].y;

            // hack to avoid clumping
            bTemp[0].x += vFinal[0].x;
            bTemp[1].x += vFinal[1].x;

            /*
             * Rotate ball positions and velocities back Reverse signs in trig expressions
             * to rotate in the opposite direction
             */
            // rotate balls
            pVector[] bFinal = { new pVector(), new pVector() };

            bFinal[0].x = cosine * bTemp[0].x - sine * bTemp[0].y;
            bFinal[0].y = cosine * bTemp[0].y + sine * bTemp[0].x;
            bFinal[1].x = cosine * bTemp[1].x - sine * bTemp[1].y;
            bFinal[1].y = cosine * bTemp[1].y + sine * bTemp[1].x;

            // update balls to screen position
            b2.position.x = b1.position.x + bFinal[1].x;
            b2.position.y = b1.position.y + bFinal[1].y;

            b1.position = pVector.add(b1.position,bFinal[0]);

            // update velocities
            b1.velocity.x = cosine * vFinal[0].x - sine * vFinal[0].y;
            b1.velocity.y = cosine * vFinal[0].y + sine * vFinal[0].x;
            b2.velocity.x = cosine * vFinal[1].x - sine * vFinal[1].y;
            b2.velocity.y = cosine * vFinal[1].y + sine * vFinal[1].x;
            
            //check if velocities are low enough to set the balls to rest
            if(b1.velocity.mag() < SBall.minVelocity) {
                b1.velocity = new pVector();
                b1.rest = true;
            }
            if(b2.velocity.mag() < SBall.minVelocity) {
                b2.velocity = new pVector();
                b2.rest = true;
            }
        }
    }

}
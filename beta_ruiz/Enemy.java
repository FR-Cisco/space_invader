public interface Enemy
{
   public void checkForCollision(Enemy e);
   public int get_X();
   public int get_Y();
   public void bounceX();
   public void bounceY();
}
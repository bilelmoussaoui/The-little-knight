package Model;

public class Weapon {
	
	private int damage;
	private int level;

	public Weapon (int damage){
		this.damage = damage;
	}

	public void setLevel(int level){
		try {
			if(level > 0) {
				this.level = level;
			} else {
				throw new Exception();
			}
		}catch (Exception e){
			this.level = 0;
		}
	}

	public int getDamage(){
        return  this.damage;
    }

	public int getLevel(){
		return this.level;
	}

}

package io.github.hopsickle.betterweaponprogression.common.item;

import java.util.Random;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BWPSwordItem extends SwordItem{

	public int curKills;
	boolean swordMastery = false;
	float baseSpeed = -2.4f;
	private static float adjustedSpeed;
	Random pRandom;
	Random pRand;
	ServerPlayer pUser;
	InteractionHand pHand;
	LivingEntity pEntity;
	
	
    public static int damage;
    public static int attackSpd;
    
	public BWPSwordItem(Tier tier, int adjustedDmg, float adjustedSpeed, Properties properties) {
		super(tier, adjustedDmg, adjustedSpeed, properties);	
	}
			@Override	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {		int killsReq;
		pHand = InteractionHand.MAIN_HAND;
		stack.hurtAndBreak(1, target, p -> p.broadcastBreakEvent(pHand)); //Working: Deletes the item at 0 durability
		//		int curDurability = stack.getOrCreateTag().getInt("Damage");		if(stack.getItem().toString() == "bwpmod_wooden_sword"){
			killsReq = 20;
		}
		else if(stack.getItem().toString() == "bwpmod_stone_sword"){
			killsReq = 40;
		}
		else if(stack.getItem().toString() == "bwpmod_iron_sword"){
			killsReq = 60;
		}
		else if(stack.getItem().toString() == "bwpmod_golden_sword"){
			killsReq = 80;
		}
		else if(stack.getItem().toString() == "bwpmod_diamond_sword"){
			killsReq = 100;
		}
		else
			killsReq = 120;
				if(target.isDeadOrDying()) {			System.out.println(stack.getItem());
//			stack.hurt(1, pRandom, pUser); //This works but only on kill			if(curKills != killsReq) 			{				if(curKills == 0) {					curKills = 1;					
					System.out.println(curKills + " / " + killsReq);					swordMastery = false;					}				else {					curKills++;					System.out.println(curKills + " / " + killsReq);					swordMastery = false;					}			}			else {				curKills = killsReq;				swordMastery = true;			//	stack.getOrCreateTag().putInt("Damage", enemyHit);			}						if(!stack.getOrCreateTag().contains("Kills", (curKills)))				stack.getOrCreateTag().putInt("Kills", (curKills));			//				stack.setDamageValue(getAdjustedDmg());	
		}	return super.hurtEnemy(stack, target, attacker);			}

/*	public static int getAdjustedDmg() 
		{
			setAdjustedDmg(swordMastery);
			return adjustedDmg;
		}		
	

	public static void setAdjustedDmg(Boolean swordMastery) {
		{ 
			if(swordMastery == true) {
				adjustedDmg = baseDmg + 1;
			}
			else
				adjustedDmg = baseDmg;
		}
	}
*/		

	public float getAdjustedSpeed() {
		adjustedSpeed = baseSpeed;
		return adjustedSpeed;
	}

	public static void setAdjustedSpeed(float adjustedSpeed) {
		BWPSwordItem.adjustedSpeed = adjustedSpeed;
	}
}

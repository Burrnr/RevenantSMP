package dev.burrnr.revenant.remnant

import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class Revenant: Remnant() {
	val playerHits = hashMapOf<Player, Int>()
	val totalHealed = hashMapOf<Player, Double>()

	@EventHandler
	fun onHit(event: EntityDamageByEntityEvent) {
		if (event.damager !is Player) return
		val player = event.damager as Player
		val remnants = getRemnantsFromRelic(player.inventory.itemInMainHand)
		if (!remnants.contains(RemnantType.REVENANT)) return
		val revenantCount = remnants.count { it == RemnantType.REVENANT }
		val amountToHeal = event.finalDamage * 0.05 * revenantCount
		if (player.health >= player.maxHealth) return
		player.health += amountToHeal
		player.sendHealthUpdate()
		totalHealed[player] = (totalHealed[player] ?: 0.0) + event.getOriginalDamage(EntityDamageEvent.DamageModifier.BASE)
		playerHits[player] = (playerHits[player] ?: 0) + 1

		if (revenantCount >= 3 && playerHits[player]!! >= 4) {
			player.spawnParticle(Particle.DUST, player.eyeLocation.x, player.eyeLocation.y, player.eyeLocation.z, 45, 3.0, 0.0, 3.0, Particle.DustOptions(Color.RED, 1f))
			totalHealed[player] = 0.0
			playerHits[player] = 0
			for (entity in event.entity.location.getNearbyLivingEntities(2.5).filter { it != player }) {
				entity.damage((totalHealed[player] ?: 0.0) * 0.7)
			}
		}
	}
}
package dev.burrnr.revenant.remnant

import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent

class Ravager: Remnant() {
	val playerHits = mutableMapOf<Player, Int>()

	@EventHandler
	fun onHit(event: EntityDamageByEntityEvent) {
		if (event.damager !is Player) return
		val player = event.damager as Player
		val remnants = getRemnantsFromRelic(player.inventory.itemInMainHand)
		if (!remnants.contains(RemnantType.RAVAGER)) return
		val ravagerCount = remnants.count { it == RemnantType.RAVAGER }

		playerHits[player] = (playerHits[player] ?: 0) + 1

		if (playerHits[player]!! == 3) {
			player.location.getNearbyLivingEntities(2.0).filter { it !is Player }.forEach {
				it.damage(event.damage * 0.25 * ravagerCount)
				player.spawnParticle(Particle.SWEEP_ATTACK, it.eyeLocation, 1)
			}
			event.damage *= 1.15
			if (ravagerCount < 3) playerHits[player] = 0
		}

		if (ravagerCount >= 3 && playerHits[player]!! >= 4) {
			event.damage *= 1.3
			event.entity.velocity = player.location.direction.multiply(5.0)
			playerHits[player] = 0
		}
	}
}
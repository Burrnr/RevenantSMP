package dev.burrnr.revenant.remnant

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ArmorMeta
import org.bukkit.util.Vector

class Bonewalker: Remnant() {
	val playerHits = hashMapOf<Player, Int>()

	@EventHandler
	fun onHit(event: EntityDamageByEntityEvent) {
		if (event.damager !is Player) return
		val remnants = getRemnantsFromRelic((event.damager as Player).inventory.itemInMainHand)
		if (!remnants.contains(RemnantType.BONEWALKER)) return
		val bonewalkerCount = remnants.count { it == RemnantType.BONEWALKER }
		val armor = event.getDamage(EntityDamageEvent.DamageModifier.ARMOR)
		event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, armor * (1 - (0.025 * bonewalkerCount)))
		playerHits[event.damager as Player] = (playerHits[event.damager as Player] ?: 0) + 1

		if (bonewalkerCount >= 3 && playerHits[event.damager as Player]!! >= 5) {
			(event.damager as Player).spawnParticle(Particle.DUST, (event.damager as Player).eyeLocation.x, (event.damager as Player).eyeLocation.y, (event.damager as Player).eyeLocation.z, 25, 1.0, 1.0, 1.0, DustOptions(Color.GRAY, 1f))
			playerHits[event.damager as Player] = 0
			if (event.entity !is Player) return
			for (item in (event.entity as Player).inventory.armorContents) {
				if (item == null) continue
				item.durability = (item.durability * .95).toInt().toShort()
			}
		}
	}
}
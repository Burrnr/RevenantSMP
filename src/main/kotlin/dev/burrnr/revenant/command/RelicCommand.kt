package dev.burrnr.revenant.command

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandCompletion
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import dev.burrnr.revenant.relic.Relic
import dev.burrnr.revenant.relic.SwordRelic
import dev.burrnr.revenant.remnant.RemnantType
import org.bukkit.entity.Player

@CommandAlias("relic")
@CommandPermission("revenant.*")
class RelicCommand: BaseCommand() {
	@Default
	fun main(player: Player) {
		player.sendMessage("input args pwease")
	}

	@Subcommand("give")
	@CommandCompletion("@relics @remnants")
	fun give(player: Player, args: Array<out String>) {
		if (args.isEmpty()) return
		val remnantTypes = args.drop(1).mapNotNull { arg -> RemnantType.entries.find { it.name.lowercase() == arg } }
		when (args[0]) {
			"none" -> {
				player.inventory.addItem(Relic().asItem())
			}
			"sword" -> {
				player.inventory.addItem(SwordRelic(remnantTypes).asItem())
			}
			"bow" -> {
				player.inventory.addItem(Relic().asItem())
			}
			"shield" -> {
				player.inventory.addItem(Relic().asItem())
			}
		}
	}
}
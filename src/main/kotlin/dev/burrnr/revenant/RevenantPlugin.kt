package dev.burrnr.revenant

import co.aikar.commands.BukkitCommandCompletionContext
import co.aikar.commands.CommandCompletions
import co.aikar.commands.PaperCommandManager
import dev.burrnr.revenant.command.RelicCommand
import dev.burrnr.revenant.relic.Relic
import dev.burrnr.revenant.remnant.Bonewalker
import dev.burrnr.revenant.remnant.Ravager
import dev.burrnr.revenant.remnant.RemnantType
import dev.burrnr.revenant.remnant.Revenant
import org.bukkit.plugin.java.JavaPlugin

class RevenantPlugin : JavaPlugin() {
	companion object {
		lateinit var instance: RevenantPlugin
		lateinit var commandManager: PaperCommandManager
	}

	override fun onEnable() {
		instance = this
		commandManager = PaperCommandManager(this)
		commandManager.registerCommand(RelicCommand())

		registerCommandCompletions()

		server.pluginManager.registerEvents(Bonewalker(), this)
		server.pluginManager.registerEvents(Revenant(), this)
		server.pluginManager.registerEvents(Ravager(), this)
	}

	private fun registerCommandCompletions() {
		val commandCompletions: CommandCompletions<BukkitCommandCompletionContext> = commandManager.commandCompletions
		commandCompletions.registerAsyncCompletion ("remnants") {
			RemnantType.entries.map { it.name.lowercase() }
		}
		commandCompletions.registerAsyncCompletion ("relics") {
			Relic.RelicType.entries.map { it.name.lowercase() }
		}
	}
}
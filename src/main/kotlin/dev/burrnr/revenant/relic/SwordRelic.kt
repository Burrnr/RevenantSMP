package dev.burrnr.revenant.relic

import dev.burrnr.revenant.remnant.RemnantType

class SwordRelic(): Relic() {
	override val type: RelicType = RelicType.SWORD

	public constructor(remnants: List<RemnantType>) : this() {
		this.remnants = remnants
	}
}
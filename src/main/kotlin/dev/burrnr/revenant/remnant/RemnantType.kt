package dev.burrnr.revenant.remnant

import dev.burrnr.revenant.relic.Relic

enum class RemnantType(val RelicType: Relic.RelicType) {
	NONE(Relic.RelicType.NONE),
	REVENANT(Relic.RelicType.SWORD),
	BONEWALKER(Relic.RelicType.SWORD),
	SHADE(Relic.RelicType.SWORD),
	ASHEN(Relic.RelicType.SWORD),
	RAVAGER(Relic.RelicType.SWORD);

	fun getRemnant(): Remnant {
		return when (this) {
			NONE -> Remnant()
			REVENANT -> Revenant()
			BONEWALKER -> Bonewalker()
			SHADE -> Shade()
			ASHEN -> Remnant()
			RAVAGER -> Ravager()
		}
	}
}
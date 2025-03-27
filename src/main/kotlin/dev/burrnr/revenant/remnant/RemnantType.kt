package dev.burrnr.revenant.remnant

import dev.burrnr.revenant.relic.Relic

enum class Remnant(val RelicType: Relic.RelicType) {
	REVENANT(Relic.RelicType.SWORD),
	BONEWALKER(Relic.RelicType.SWORD),
	SHADE(Relic.RelicType.SWORD),
	ASHEN(Relic.RelicType.SWORD),
	RAVAGER(Relic.RelicType.SWORD),
}
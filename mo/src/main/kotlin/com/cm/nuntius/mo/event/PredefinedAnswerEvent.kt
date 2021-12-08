package com.cm.nuntius.mo.event

import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswer

class PredefinedAnswerEvent(payload: PredefinedAnswer) : Event<PredefinedAnswer>("PREDEFINED_ANSWER", payload)
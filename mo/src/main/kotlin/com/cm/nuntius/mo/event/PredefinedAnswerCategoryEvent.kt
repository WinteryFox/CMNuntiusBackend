package com.cm.nuntius.mo.event

import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswerCategory

class PredefinedAnswerCategoryEvent(payload: PredefinedAnswerCategory) : Event<PredefinedAnswerCategory>("PREDEFINED_ANSWER", payload)
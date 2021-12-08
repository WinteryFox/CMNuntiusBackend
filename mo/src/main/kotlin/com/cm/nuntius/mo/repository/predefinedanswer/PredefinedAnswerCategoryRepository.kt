package com.cm.nuntius.mo.repository.predefinedanswer

import com.cm.nuntius.mo.json.predefinedanswer.PredefinedAnswer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PredefinedAnswerCategoryRepository : CoroutineCrudRepository<PredefinedAnswer, String>

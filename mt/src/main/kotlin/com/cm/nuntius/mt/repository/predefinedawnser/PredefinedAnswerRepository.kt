package com.cm.nuntius.mt.repository.predefinedawnser

import com.cm.nuntius.mt.json.predefinedanswer.PredefinedAnswer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PredefinedAnswerRepository : CoroutineCrudRepository<PredefinedAnswer, String>
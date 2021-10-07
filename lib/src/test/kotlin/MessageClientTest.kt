import com.cm.nuntius.lib.MessagingClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class MessageClientTest {
    private val client = MessagingClient(System.getenv("CM_TOKEN"))

    // TODO: Not sure how we're going to test this for production, this should not be left in this state...
    @Test
    fun testSendMessage() = runBlocking {
        val r = "0006"
        val response = client.sendMessage {
            message {
                reference = r
                body {
                    type = "auto"
                    content = "Heyy"
                }
                to {
                    number = "851427679786192896"
                }
                from = "1435529519025344515"
                allowedChannels("Twitter")
            }
        }
        assertEquals(response.messages[0].status, "Accepted")
        assertEquals(response.messages[0].reference, r)
    }
}

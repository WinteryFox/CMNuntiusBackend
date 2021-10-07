import com.cm.nuntius.lib.MessagingClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class MessageClientTest {
    private val client = MessagingClient(System.getenv("CM_TOKEN"))

    @Test
    fun testSendMessage() = runBlocking {
        client.sendMessage {
            message {
                body {
                    type = "auto"
                    content = "Thanks, I like yours too :)"
                }
                to {
                    number = "851427679786192896"
                }
                from = "1435529519025344515"
                allowedChannels("Twitter")
            }
        }
    }
}

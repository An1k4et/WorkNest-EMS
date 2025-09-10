class ChatbotWidget {
    constructor() {
        this.isOpen = false;
        this.messages = [];
        this.init();
    }

    init() {
        this.bindEvents();
        this.showNotificationAfterDelay();
    }

    bindEvents() {
        const toggle = document.getElementById('chatbotToggle');
        const input = document.getElementById('chatbotInput');
        const sendButton = document.getElementById('sendButton');

        toggle.addEventListener('click', () => this.toggleChat());
        input.addEventListener('keypress', (e) => { if (e.key === 'Enter') this.sendMessage(); });
        input.addEventListener('input', () => this.updateSendButton());
        sendButton.addEventListener('click', () => this.sendMessage());

        // Close chat when clicking outside
        document.addEventListener('click', (e) => {
            const container = document.getElementById('chatbotContainer');
            if (!container.contains(e.target) && this.isOpen) this.closeChat();
        });
    }

    toggleChat() {
        this.isOpen ? this.closeChat() : this.openChat();
    }

    openChat() {
        this.isOpen = true;
        document.getElementById('chatbotWindow').classList.add('active');
        document.getElementById('chatbotToggle').classList.add('active');
        document.getElementById('notificationBadge').style.display = 'none';
        setTimeout(() => document.getElementById('chatbotInput').focus(), 300);
    }

    closeChat() {
        this.isOpen = false;
        document.getElementById('chatbotWindow').classList.remove('active');
        document.getElementById('chatbotToggle').classList.remove('active');
    }

    async sendMessage() {
        const input = document.getElementById('chatbotInput');
        const message = input.value.trim();
        if (!message) return;

        this.addMessage(message, 'user');
        input.value = '';
        this.updateSendButton();

        // Hide quick actions if exist
        const quickActions = document.getElementById('quickActions');
        if (quickActions) quickActions.style.display = 'none';

        // Show typing indicator
        this.showTypingIndicator();

        // Get bot response from API
        const botResponse = await this.getBotResponse(message);

        this.hideTypingIndicator();
        this.addMessage(botResponse, 'bot');
    }

    addMessage(content, sender) {
        const messagesContainer = document.getElementById('chatbotMessages');
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${sender}`;

        const avatar = document.createElement('div');
        avatar.className = 'message-avatar';
        avatar.textContent = sender === 'bot' ? '🤖' : '👤';

        const messageContent = document.createElement('div');
        messageContent.className = 'message-content';
        messageContent.textContent = content;

        messageDiv.appendChild(avatar);
        messageDiv.appendChild(messageContent);
        messagesContainer.appendChild(messageDiv);

        messagesContainer.scrollTop = messagesContainer.scrollHeight;
        this.messages.push({ content, sender, timestamp: new Date() });
    }

    async getBotResponse(userMessage) {
        try {
            const response = await fetch('/bot/ask', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ question: userMessage })
            });
            const data = await response.json();
            return data.result || "Sorry, I didn't get a response.";
        } catch (err) {
            console.error(err);
            return "Sorry, something went wrong. Please try again later.";
        }
    }

    showTypingIndicator() {
        const typing = document.getElementById('typingIndicator');
        if (typing) typing.style.display = 'flex';
    }

    hideTypingIndicator() {
        const typing = document.getElementById('typingIndicator');
        if (typing) typing.style.display = 'none';
    }

    updateSendButton() {
        const input = document.getElementById('chatbotInput');
        const button = document.getElementById('sendButton');
        button.disabled = !input.value.trim();
    }

    showNotificationAfterDelay() {
        setTimeout(() => {
            if (!this.isOpen) document.getElementById('notificationBadge').style.display = 'flex';
        }, 10000);
    }
}

// Quick Actions
function sendQuickMessage(message) {
    const input = document.getElementById('chatbotInput');
    input.value = message;
    chatbot.sendMessage();
}

// Initialize chatbot
let chatbot;
document.addEventListener('DOMContentLoaded', () => {
    chatbot = new ChatbotWidget();
});

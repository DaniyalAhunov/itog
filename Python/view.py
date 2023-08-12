class NoteView:
    def show_notes(self, notes):
        print("Список заметок:")
        for note in notes:
            print(f"ID: {note['id']}, Заголовок: {note['title']}, Время: {note['timestamp']}")

    def show_note_details(self, note):
        print(f"ID: {note['id']}")
        print(f"Заголовок: {note['title']}")
        print(f"Тело: {note['body']}")
        print(f"Время: {note['timestamp']}")

    def show_message(self, message):
        print(message)

    def get_input(self, prompt):
        return input(prompt)

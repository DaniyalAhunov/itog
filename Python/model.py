import json
import os
import datetime

class NoteModel:
    def __init__(self):
        self.notes = []
        self.file_path = os.path.join(os.path.dirname(__file__), 'notes.json') 

    def add_note(self, title, body):
        timestamp = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        note = {
            'id': len(self.notes) + 1,
            'title': title,
            'body': body,
            'timestamp': timestamp
        }
        self.notes.append(note)

    def get_notes(self):
        return self.notes

    def get_notes_by_date(self, date):
        filtered_notes = [note for note in self.notes if note['timestamp'].startswith(date)]
        return filtered_notes

    def edit_note(self, note_id, title, body):
        note = self.notes[note_id - 1]
        note['title'] = title
        note['body'] = body
        note['timestamp'] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

    def delete_note(self, note_id):
        del self.notes[note_id - 1]

    def save_notes(self):
        with open(self.file_path, 'w') as file:
            json.dump(self.notes, file)


    def load_notes(self):
        if os.path.exists(self.file_path):
            with open(self.file_path, 'r') as file:
                self.notes = json.load(file)
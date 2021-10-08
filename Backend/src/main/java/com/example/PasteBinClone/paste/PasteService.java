package com.example.PasteBinClone.paste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PasteService {

    @Autowired
    private PasteRepository PasteRepository;

    public PasteService(PasteRepository PasteRepository) {
        this.PasteRepository = PasteRepository;
    }

    public List<Paste> getPastesListByUser(String user) {
        return PasteRepository.findPastesByUser(user);
    }

    public Optional<Paste> getPasteById(Long pasteId) {
        return PasteRepository.findPasteById(pasteId);
    }

    public List<Paste> getPastes() {
        return PasteRepository.findAll();
    }

    public void addNewPaste(Paste paste) {
        Optional<Paste> pasteOptional = PasteRepository.findPastebyTitle(paste.getTitle());
        if (pasteOptional .isPresent()) {
            throw new IllegalStateException("Title is taken");
        }
        paste.setDatecreated(LocalDateTime.now(ZoneId.of("Europe/Bucharest")));
        PasteRepository.save(paste);
    }

    public void deletePaste(Long pasteId) {
        boolean exists = PasteRepository.existsById(pasteId);
        if (!exists) {
            throw new IllegalStateException(
                    "Paste with id " + pasteId + " does not exists");
        }
        PasteRepository.deleteById(pasteId);
    }

    @Transactional
    public void updatePaste(Long pasteId,
                            String title,
                            String text) {
        Paste paste = PasteRepository.findById(pasteId)
                .orElseThrow(()-> new IllegalStateException(
                        "Paste with id " + pasteId + "does not exists"));
        if (title != null && title.length() > 0 && !Objects.equals(paste.getTitle(), title)) {
            paste.setTitle(title);
        }
        if (text != null && text.length() > 0 && !Objects.equals(paste.getText(), text)) {
            paste.setText(text);
        }
            paste.setDatecreated(LocalDateTime.now(ZoneId.of("Europe/Bucharest")));
    }
}

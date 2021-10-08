package com.example.PasteBinClone.paste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "paste")
public class PasteController {

    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }
    @GetMapping(path = "list/pastes")
    public List<Paste> getPastes() {
        return pasteService.getPastes();
    }

    @GetMapping(path = "list/user/{user}")
    public List<Paste> getUserPastes(
            @PathVariable("user") String user) {
        return pasteService.getPastesListByUser(user);
    }

    @GetMapping(path = "list/id/{pasteId}")
    public Optional<Paste> getPasteId(
            @PathVariable("pasteId") Long pasteId) {
        return pasteService.getPasteById(pasteId);
    }

    @PostMapping(path = "create")
    public void registerNewPaste(@RequestBody Paste paste) {
        pasteService.addNewPaste(paste);
    }

    @DeleteMapping(path = "delete/{pasteId}")
    public void deletePaste(
            @PathVariable("pasteId") Long pasteId) {
        pasteService.deletePaste(pasteId);
    }

    @PutMapping(path = "update/{pasteId}")
    public void updatePaste(
            @PathVariable("pasteId") Long pasteId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String text) {
        pasteService.updatePaste(pasteId, title, text);
    }
}

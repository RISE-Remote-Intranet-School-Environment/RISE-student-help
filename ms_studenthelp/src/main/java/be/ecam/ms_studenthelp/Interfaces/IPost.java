package be.ecam.ms_studenthelp.Interfaces;

import be.ecam.ms_studenthelp.Object.Author;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public interface IPost {
    @NonNull String getId();
    @NonNull Author getAuthor();
    int getUpVotes();
    int getDownVotes();

    @NonNull LocalDateTime getDatePosted();
    @NonNull LocalDateTime getDateModified();
    @NonNull String getContent();
    IPost getParent();

    void setContent(@NonNull String content);
    void setUpVotes(int upvotes);
    void setDownVotes(int downvotes);
    void setDateModified(@NonNull LocalDateTime dateModified);
    void setParent(IPost parent);

    void incrementUpVotes(int upvotes);
    void incrementDownVotes(int downvotes);
    void decrementUpVotes(int upvotes);
    void decrementDownVotes(int downvotes);
}

package gcastle.com.accessibilitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.TextView;

public class AnnounceActivity extends Activity {

    Button mSendAnnounceEventTypeButton;
    Button mSendAnnounceEventButton;
    Button mAnnounceForAccessibility;

    int mCount = 0;
    Button mChangePoliteTextView;
    TextView mPoliteTextView;


    private TextView mAssertiveTextView;
    private Button mChangeAssertiveTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announce);

        /*
         * This can be set in the AndroidManifest under android:label="[TEXT HERE]". See
         * MainActivity for an example of this.
         */
        setTitle("Announce Activity");

        /**
         * sendAccessibilityEvent() type
         *
         * Perform a type of event on the view.
         *
         * Doesn't actually seem to have any visual effect when you perform TYPE_ANNOUNCEMENT
         * on a Button. Uses the Views AccessibilityDelegate.
         */
        mSendAnnounceEventTypeButton = findViewById(R.id.sendAccessibilityAnnouncement);
        mSendAnnounceEventTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendAnnounceEventTypeButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_ANNOUNCEMENT);
            }
        });

        /**
         * sendAccessibilityEventUnchecked()
         *
         * Perform a specific event on the view.
         *
         * Doesn't actually seem to have any visual effect when you perform TYPE_ANNOUNCEMENT
         * on a Button.
         */
        mSendAnnounceEventButton = findViewById(R.id.sendAccessibilityAnnouncementEvent);
        mSendAnnounceEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessibilityEvent event = AccessibilityEvent.obtain();
                event.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
                event.setClassName(getClass().getName());
                event.setPackageName(getApplication().getPackageName());
                event.getText().add("Event Text");

                mSendAnnounceEventButton.sendAccessibilityEventUnchecked(event);
            }
        });

        /**
         * announceForAccessibility(String)
         *
         * Announces the string you feed into the method, will use the Views AccessibilityDelegate
         * to perform the announcement.
         */
        mAnnounceForAccessibility = findViewById(R.id.announceForAccessibility);
        mAnnounceForAccessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnnounceForAccessibility.announceForAccessibility("Accessibility is Great");
            }
        });

        /**
         * android:accessibilityLiveRegion="polite"
         *
         * When the value of a "polite" field is changed, it will wait for other Accessibility
         * events to finish speaking before announcing it's change.
         */
        mChangePoliteTextView = findViewById(R.id.changePoliteTextView);
        mPoliteTextView = findViewById(R.id.politeTextView);
        mChangePoliteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPoliteTextView.setText("I'm polite: " + mCount);
                mCount++;
            }
        });

        /**
         * android:accessibilityLiveRegion="assertive"
         *
         * When the value of a "assertive" field is changed, it will interrupt other Accessibility
         * events to announcing it's change.
         */
        mChangeAssertiveTextView = findViewById(R.id.changeAssertiveTextView);
        mAssertiveTextView = findViewById(R.id.assertiveTextView);
        mChangeAssertiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAssertiveTextView.setText("I'm assertive: " + mCount);
                mCount++;
            }
        });
    }
}

package demo.chenys101.com.grpcandroiddemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.MessageFormat;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.routeguideexample.Feature;
import io.grpc.routeguideexample.Point;
import io.grpc.routeguideexample.RouteGuideGrpc;
import io.grpc.routeguideexample.RouteGuideUtil;

public class MainActivity extends AppCompatActivity {

    private EditText mHostEdit;
    private EditText mPortEdit;
    private Button mStartRouteGuideButton;
    private Button mExitRouteGuideButton;
    private Button mGetFeatureButton;
    private TextView mResultText;

    private ManagedChannel mChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHostEdit = (EditText) findViewById(R.id.host_edit_text);
        mPortEdit = (EditText) findViewById(R.id.port_edit_text);
        mStartRouteGuideButton = (Button) findViewById(R.id.start_route_guide_button);
        mExitRouteGuideButton = (Button) findViewById(R.id.exit_route_guide_button);
        mGetFeatureButton = (Button) findViewById(R.id.get_feature_button);
        mResultText = (TextView) findViewById(R.id.result_text);
        disableButtons();
    }

    public void startRouteGuide(View view) {
        String host = mHostEdit.getText().toString();
        String portStr = mPortEdit.getText().toString();
        if (TextUtils.isEmpty(host) || TextUtils.isEmpty(portStr)) {
            mResultText.setText("Please input host or port!");
            return;
        }
        int port = Integer.valueOf(portStr);
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mHostEdit.getWindowToken(), 0);
        mChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        mHostEdit.setEnabled(false);
        mPortEdit.setEnabled(false);
        mStartRouteGuideButton.setEnabled(false);
        enableButtons();
        mResultText.setText("");
    }

    public void exitRouteGuide(View view) {
        mChannel.shutdown();
        disableButtons();
        mHostEdit.setEnabled(true);
        mPortEdit.setEnabled(true);
        mStartRouteGuideButton.setEnabled(true);
    }

    private void disableButtons() {
        mGetFeatureButton.setEnabled(false);
        mExitRouteGuideButton.setEnabled(false);
    }

    private void enableButtons() {
        mExitRouteGuideButton.setEnabled(true);
        mGetFeatureButton.setEnabled(true);
    }

    public void getFeature(View view) {
        if (mStartRouteGuideButton.isEnabled()) {
            mResultText.setText("Please Press Start Route Guide first!");
            return;
        }

        StringBuffer logs = new StringBuffer();
        appendLogs(logs, "*** GetFeature: lat={0} lon={1}", 409146138, -746188906);

        RouteGuideGrpc.RouteGuideBlockingStub blockingStub = RouteGuideGrpc.newBlockingStub(mChannel);
        Point request = Point.newBuilder().setLatitude(409146138).setLongitude(-746188906).build();

        Feature feature;
        feature = blockingStub.getFeature(request);
        if (RouteGuideUtil.exists(feature)) {
            appendLogs(logs, "Found feature called \"{0}\" at {1}, {2}",
                    feature.getName(),
                    RouteGuideUtil.getLatitude(feature.getLocation()),
                    RouteGuideUtil.getLongitude(feature.getLocation()));
        } else {
            appendLogs(logs, "Found no feature at {0}, {1}",
                    RouteGuideUtil.getLatitude(feature.getLocation()),
                    RouteGuideUtil.getLongitude(feature.getLocation()));
        }
        Log.d("logs:", logs.toString());
        mResultText.setText(logs.toString());
        enableButtons();
    }



    private static void appendLogs(StringBuffer logs, String msg, Object... params) {
        if (params.length > 0) {
            logs.append(MessageFormat.format(msg, params));
        } else {
            logs.append(msg);
        }
        logs.append("\n");
    }
}

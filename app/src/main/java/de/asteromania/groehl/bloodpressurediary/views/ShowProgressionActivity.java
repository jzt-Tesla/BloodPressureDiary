package de.asteromania.groehl.bloodpressurediary.views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.asteromania.groehl.bloodpressurediary.R;
import de.asteromania.groehl.bloodpressurediary.database.DataItemDatabaseAccess;
import de.asteromania.groehl.bloodpressurediary.database.DatabaseService;
import de.asteromania.groehl.bloodpressurediary.domain.DataItem;
import de.asteromania.groehl.bloodpressurediary.domain.DataItemType;

public class ShowProgressionActivity extends AppCompatActivity {

    public static final String EXTRA = "de.asteromania.groehl.bloodpressurediary.ShowProgressionActivity.EXTRA";
    private static final int MAX_DATA_POINTS = 30;
    private static final double GRAPH_MARGIN = 10;
    private static final int MAX_HORIZONTAL_LABELS = 5;

    DataItemDatabaseAccess database = DatabaseService.getDataItemDatabaseAccess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progression);

        setupView();

    }

    @Override
    protected void onResume() {
        setupView();
        super.onResume();
    }

    private void setupView() {

        String extraString = getIntent().getStringExtra(EXTRA);

        DataItemType currentType = DataItemType.valueOf(extraString);

        if (currentType == null) {
            finish();
        }

        GraphView graph = (GraphView) findViewById(R.id.graphProgression);
        graph.removeAllSeries();

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this, new SimpleDateFormat("dd.MM", Locale.ENGLISH)));
        graph.getGridLabelRenderer().setHumanRounding(false);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(database.getMinimumDate(currentType));
        graph.getViewport().setMaxX(database.getMaximumDate(currentType));



        switch (currentType)
        {
        case SYSTOLE:
        case DIASTOLE:
            addDataSeriesToGraph(DataItemType.SYSTOLE, graph, Color.BLUE);
            addDataSeriesToGraph(DataItemType.DIASTOLE, graph, Color.BLACK);
            graph.getViewport().setMaxY(database.getMaximumValue(DataItemType.SYSTOLE)+ GRAPH_MARGIN);
        break;
        default:
            addDataSeriesToGraph(currentType, graph, Color.BLUE);
            graph.getViewport().setMaxY(database.getMaximumValue(currentType)+ GRAPH_MARGIN);
            break;
        }


    }

    private void addDataSeriesToGraph(DataItemType type, GraphView graph, int color)
    {
        LineGraphSeries<DataPoint> s = new LineGraphSeries<>();
        s.setTitle(getString(type.getText()));
        s.setColor(color);
        List<DataItem> list = (List<DataItem>) database.getLastNItemsByType(MAX_DATA_POINTS, type);
        graph.getGridLabelRenderer().setNumHorizontalLabels(list.size()<MAX_HORIZONTAL_LABELS?list.size():MAX_HORIZONTAL_LABELS);
        for(DataItem d : list)
        {
            s.appendData(new DataPoint(d.getDate(), d.getValue()), true, MAX_DATA_POINTS);
        }

        graph.addSeries(s);
    }
}

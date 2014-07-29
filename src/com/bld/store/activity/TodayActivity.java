package com.bld.store.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BarAlertSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.style.ScaleXSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.bld.store.R;
import com.bld.task.TaskQueue;



@SuppressLint("HandlerLeak")
public class TodayActivity extends Activity {
	
	private LinearLayout chartLyp1;
	private LinearLayout chartLyp2;
	private GraphicalView view1 = null;
	private GraphicalView view2 = null;
	private Button zoombtn1 = null;
	private Button zoombtn2 = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_chart);
        findView();
        setView();
    }

    
    private void findView(){


    	chartLyp2 = (LinearLayout)findViewById(R.id.chartLyp2);

    	view2 = (GraphicalView)findViewById(R.id.chartView2);

    	zoombtn2 = (Button)findViewById(R.id.btn_Zoom2);
    }
    
    private void setView(){
    	
    	if(view2 != null){
    		setChartView2Data();
    	}
    	
    	
    	
    	if(zoombtn2 != null){
    		zoombtn2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutParams lyp2 = (LayoutParams)chartLyp2.getLayoutParams();
					//LayoutParams lyp1 = (LayoutParams)chartLyp1.getLayoutParams();
					if(lyp2.height == LayoutParams.FILL_PARENT){
						zoombtn2.setText("放大");
						lyp2.height = 0;
						//lyp1.weight = 1;
					} else {
						lyp2.height = LayoutParams.FILL_PARENT;
						zoombtn2.setText("缩小");
						//lyp1.weight = 0;
					}
					//chartLyp1.setLayoutParams(lyp1);
					chartLyp2.setLayoutParams(lyp2);
					view2.reStartGif();
				}
			});
    	}
    }


	//���ߵ�ͼ��
	private void setChartView2Data() {
		String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };//�������ߵ�����
	    List<double[]> x = new ArrayList<double[]>();
	    for (int i = 0; i < titles.length; i++) {
	      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });//x��
	    }
	    List<double[]> values = new ArrayList<double[]>();
	    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
	        13.9 });//y��  �м����߾��м���
	    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
	    values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
	    values.add(new double[] { 9, 10, 15, 3, 7, 16, 2, 6, 0, 3, 23, 10 });
	    
	    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.RED };//ÿ���ߵ���ɫ
	    PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT,
	        PointStyle.POINT, PointStyle.POINT };//��ʽ �Ե�Ϊ��ʽ
	    
	    XYMultipleSeriesRenderer renderer = ChartViewTools.buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//��ÿ���������
	    }
	    
//	    renderer.setMargins(new int[] { 120, 60, 180, 40 });//�ϣ����£���
	    
	    ChartViewTools.setChartSettings(renderer, "销售统计", "时间", "金额", 0.5 , 12.5, -10, 40,
	        Color.BLUE, Color.GRAY);
	    renderer.setZoomEnabled(false, false);//�Ƿ�Ŵ�
	    renderer.setGifStart(true);//�Ƿ�̬
	    renderer.setXLabels(0);//x��Ŀ̶ȴ�0��ʼ
	    renderer.setYLabels(10);//����Y��ÿ���̶ȵĳ���Ϊ����ֵ
	    renderer.setShowGrid(false);
	    renderer.setXLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    renderer.setYLabelsPadding(10);//y�������Y��ľ���
	    renderer.setXLabelsAngle(-45);//����x��ı����������б����Ϊ����Ϊ��
		renderer.setShowLegend(false);
	    renderer.setPanLimits(new double[] { -10, 20, -10, 40 });//�Ŵ�ķ�Χ
	    renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });//ƫ�Ƶķ�Χ
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0, Color.BLACK);
	    renderer.setApplyBackgroundColor(true);
	    renderer.setBackgroundColor(Color.argb(0, 220, 228, 234));
	    renderer.setMarginsColor(Color.argb(0, 220, 228, 234));
	    for (int i = 0; i < 12; i++){
	    	 renderer.addXTextLabel(i+1,9+i+":00");
	    }
	    length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setFillBelowLine(i == 2);// length - 1);
			seriesRenderer.setGifStart(true);
			seriesRenderer.setFillBelowLineColor(colors[i]);
			seriesRenderer.setLineWidth(2.5f);
			seriesRenderer.setDisplayChartValues(true);
			seriesRenderer.setChartValuesTextSize(10f);
		}
		
	    XYMultipleSeriesDataset dataset = ChartViewTools.buildDataset(titles, x, values);//��ֵ����
	    XYSeries series = dataset.getSeriesAt(0);
	    series.addAnnotation("Vacation", 6, 30);
	    LineChart chart = new LineChart(dataset, renderer);
	    view2.setChart(chart);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	

}

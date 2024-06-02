package interfaz;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import galeria.compraYsubasta.Compra;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizarVentas extends ApplicationFrame {

    
	private static final long serialVersionUID = 1L;
	public VisualizarVentas(String title, Map<String, Integer> ventasDiarias) {
        super(title);
        JFreeChart chart = crearGraficoBarras(ventasDiarias);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));
        setContentPane(chartPanel);
    }

    private JFreeChart crearGraficoBarras(Map<String, Integer> ventasDiarias) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : ventasDiarias.entrySet()) {
            String fecha = entry.getKey();
            dataset.addValue(entry.getValue(), "Ventas", fecha);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Ventas Diarias",
                "Fecha",
                "Número de Ventas",
                dataset
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                org.jfree.chart.axis.CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }
    public static Map<String, Integer> contarVentasDiarias(List<Compra> ventas) {
        Map<String, Integer> ventasDiarias = new HashMap<>();

        for (Compra compra : ventas) {
            String fecha=compra.getFecha();
            ventasDiarias.put(fecha, ventasDiarias.getOrDefault(fecha, 0) + 1);
        }

        return ventasDiarias;
    }

}


import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class MarkDown {

	public static void main(String[] args) {
		List<Extension> extensions = Arrays.asList(TablesExtension.create());
		Parser parser = Parser.builder()
		        .extensions(extensions)
		        .build();
		HtmlRenderer renderer = HtmlRenderer.builder()
		        .extensions(extensions)
		        .build();
		
	
		Node document = parser.parse("# Blockly [![Build Status]( https://travis-ci.org/google/blockly.svg?branch=master)](https://travis-ci.org/google/blockly)\n" + 
				"\n" + 
				"\n" + 
				"Google's Blockly is a web-based, visual programming editor.  Users can drag\n" + 
				"blocks together to build programs.  All code is free and open source.\n" + 
				"\n" + 
				"**The project page is https://developers.google.com/blockly/**\n" + 
				"\n" + 
				"![](https://developers.google.com/blockly/images/sample.png)\n" + 
				"\n" + 
				"Blockly has an active [developer forum](https://groups.google.com/forum/#!forum/blockly). Please drop by and say hello. Show us your prototypes early; collectively we have a lot of experience and can offer hints which will save you time.\n" + 
				"\n" + 
				"Help us focus our development efforts by telling us [what you are doing with\n" + 
				"Blockly](https://developers.google.com/blockly/registration). The questionnaire only takes\n" + 
				"a few minutes and will help us better support the Blockly community.\n" + 
				"\n" + 
				"Want to contribute? Great! First, read [our guidelines for contributors](https://developers.google.com/blockly/guides/modify/contributing).");

		String html = renderer.render(document); // "<p>This is <em>Sparta</em></p>\n"
		//System.out.println(html);

		JFrame jFrame = new JFrame();
		jFrame.setPreferredSize(new Dimension(500, 500));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		JFXPanel jfxPanel = new JFXPanel();

		jFrame.add(jfxPanel);
		jFrame.pack();
		jFrame.setVisible(true);
		// Creation of scene and future interactions with JFXPanel
		// should take place on the JavaFX Application Thread
		
		String template = "<html>\n" + 
				"<header>\n" + 
				"	\n" + 
				"	<link rel=\"stylesheet\" href=\"github-markdown.css\">\n" + 
				"</header>\n" + 
				"\n" + 
				"<body class=\"markdown-body\" style=\"margin:60px\">\n" + 
				html
				+ "</body>\n" + 
				"</html>\n" + 
				"";
		
		
		System.out.println(template);
		java.net.URL url = MarkDown.class.getClassLoader().getResource("github-markdown.css");

		System.out.println(url);
		Platform.runLater(() -> {
			WebView webView = new WebView();
			jfxPanel.setScene(new Scene(webView));
			webView.getEngine().setUserStyleSheetLocation(url.toString());
			webView.getEngine().loadContent(template);
			
		});
	}
	
}

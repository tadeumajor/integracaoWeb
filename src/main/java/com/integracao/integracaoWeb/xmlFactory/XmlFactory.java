package com.integracao.integracaoWeb.xmlFactory;

import com.integracao.integracaoWeb.response.PunkApiBeerResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class XmlFactory {

    public static Workbook createExcellWorkbook(List<PunkApiBeerResponse> cervejas) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório de cervejas");

        int rowNum = 0;
        int numTotalCol = 4;

        Row headerRow = sheet.createRow(rowNum++);
        CellStyle headerStyle = createHeaderStyle(workbook);
        createHeaderCells(headerRow,headerStyle,"Id","Nome","Abv","IBU","Descrição");

        for(PunkApiBeerResponse cerveja:cervejas){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cerveja.getId());
            row.createCell(1).setCellValue(cerveja.getName());
            row.createCell(2).setCellValue(cerveja.getAbv());
            row.createCell(3).setCellValue(cerveja.getIbu());
            row.createCell(4).setCellValue(cerveja.getDescription());
        }
        adjustRows(sheet);

        // Adicionar autofiltro ao cabeçalho
        sheet.setAutoFilter(new CellRangeAddress(headerRow.getRowNum(), headerRow.getRowNum(), 0, numTotalCol));

        return workbook;
    }

    private static void createHeaderCells(Row row, CellStyle style, String... cabecalho) {
        for (int columnIndex = 0; columnIndex < cabecalho.length; columnIndex++) {
            Cell cell = row.createCell(columnIndex);
            cell.setCellValue(cabecalho[columnIndex]);
            cell.setCellStyle(style);
        }
    }

    //cria estilização com fonte e cor de fundo
    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //adicionar formatacao para as linhas posteriormente tbm
        style.setWrapText(true);
        return style;
    }
    private static void adjustRows(Sheet sheet) {
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

}

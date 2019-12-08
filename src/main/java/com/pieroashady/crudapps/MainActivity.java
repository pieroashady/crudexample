package com.pieroashady.crudapps;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.pieroashady.crudapps.APIService.APIClient;
import com.pieroashady.crudapps.APIService.APIInterfaceRest;
import com.pieroashady.crudapps.Models.deletedata.StatusDelete;
import com.pieroashady.crudapps.Models.getdataid.StatusId;
import com.pieroashady.crudapps.Models.insertdata.StatusInsert;
import com.pieroashady.crudapps.Models.updatedata.StatusUpdate;
import com.pieroashady.crudapps.Models.viewdata.Datum;
import com.pieroashady.crudapps.Models.viewdata.StatusGet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText editNamaKategori;
    String namaKategori;
    LinearLayout linearLayout;
    Context context = this;
    TextView txtView;
    ProgressDialog loading;
    APIInterfaceRest apiInterfaceRest;
    StatusGet status;
    List<Datum> dataKategori;
    TableLayout tableLayout;
    ArrayList<Button> editButton = new ArrayList<>();
    ArrayList<Button> deleteButton = new ArrayList<>();
    TextView headerId, headerNim, headerNama, headerAlamat, headerAction,
            viewId, viewNim;
    TableRow tableRow;
    int idKategori;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear);


        viewId = new TextView(MainActivity.this);
        viewNim = new TextView(MainActivity.this);

        tableLayout = findViewById(R.id.tableLayout);
        tableRow = new TableRow(this);
        tableRow.setBackgroundColor(Color.CYAN);

        headerId = new TextView(MainActivity.this);
        headerNim = new TextView(MainActivity.this);
        headerAction = new TextView(MainActivity.this);

        headerId.setText("No");
        headerId.setPadding(5, 1, 5, 1);
        headerNim.setText("Nama Kategori");
        headerNim.setPadding(5, 1, 5, 1);
        headerAction.setText("Action");
        headerAction.setPadding(5, 1, 5, 1);

        tableRow.addView(headerId);
        tableRow.addView(headerNim);
        tableRow.addView(headerAction);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener((v) -> {
            showAlert(namaKategori);
        });

        getData();
    }

    private void getData() {
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        showLoading("Get Data ...");
        Call<StatusGet> call = apiInterfaceRest.getData();
        call.enqueue(new Callback<StatusGet>() {
            @Override
            public void onResponse(Call<StatusGet> call, Response<StatusGet> response) {
                loading.dismiss();

                status = response.body();
                dataKategori = response.body().getData();

                if (status.getStatus().equals(1)) {
                    for (int i = 0; i < dataKategori.size(); i++) {
                        int no = i + 1;
                        String nomor = String.valueOf(no);
                        String kategori = dataKategori.get(i).getNamaKategori();
                        String id = dataKategori.get(i).getIdKategori().toString();
                        int idKategori = dataKategori.get(i).getIdKategori();

                        layout(no, nomor, kategori);

                        editButton.add(i, new Button(MainActivity.this));
                        editButton.get(i).setId(Integer.parseInt(id));
                        editButton.get(i).setTag("Edit");
                        editButton.get(i).setText("Edit");
                        editButton.get(i).setBackgroundResource(R.drawable.btn_rounded_accent);
                        editButton.get(i).setTextColor(Color.WHITE);
                        editButton.get(i).setOnClickListener((v) -> {
                            getDataById(idKategori);
                        });
                        tableRow.addView(editButton.get(i));

                        deleteButton.add(i, new Button(MainActivity.this));
                        deleteButton.get(i).setId(Integer.parseInt(id));
                        deleteButton.get(i).setTag("Delete");
                        deleteButton.get(i).setText("Delete");
                        deleteButton.get(i).setBackgroundResource(R.drawable.btn_rounded_accent);
                        deleteButton.get(i).setTextColor(Color.WHITE);
                        deleteButton.get(i).setOnClickListener((v) -> {
                            showDeleteAlert(kategori, idKategori);
                        });
                        tableRow.addView(deleteButton.get(i));

                        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT));

                    }
                } else {
                    loading.dismiss();
                    showSnackbar("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<StatusGet> call, Throwable t) {
                loading.dismiss();
                Log.d("debug", t.getMessage());
                showSnackbar("Connection Error, try again later");
            }
        });
    }

    private void deleteData(int id) {
        showLoading("Deleting data...");
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        Call<StatusDelete> call = apiInterfaceRest.deleteData(id);
        call.enqueue(new Callback<StatusDelete>() {
            @Override
            public void onResponse(Call<StatusDelete> call, Response<StatusDelete> response) {
                loading.dismiss();

                StatusDelete statusDelete;
                statusDelete = response.body();

                if (statusDelete.getStatus().equals(1)) {
                    Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(getIntent());
                } else {
                    loading.dismiss();
                    Toast.makeText(MainActivity.this, "GAGAL", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StatusDelete> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "KONEKSI ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataById(int id) {
        showLoading("Wait...");
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        Call<StatusId> call = apiInterfaceRest.getDataById(id);
        call.enqueue(new Callback<StatusId>() {
            @Override
            public void onResponse(Call<StatusId> call, Response<StatusId> response) {
                loading.dismiss();

                StatusId statusId;
                statusId = response.body();
                if (statusId.getStatus().equals(1)) {
                    idKategori = statusId.getData().getIdKategori();
                    namaKategori = statusId.getData().getNamaKategori();
                    showEditAlert(idKategori, namaKategori);
                    showSnackbar("Edit NIM " + namaKategori + " ?");
                } else {
                    loading.dismiss();
                    showSnackbar("NIM not found");
                }
            }

            @Override
            public void onFailure(Call<StatusId> call, Throwable t) {
                loading.dismiss();
                Log.d("DEBUG", t.getMessage());
                showSnackbar("Connection Error, try again");
            }
        });
    }

    private void saveData(String kategori) {
        showLoading("Save data...");
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        Call<StatusInsert> call = apiInterfaceRest.saveData(kategori);
        call.enqueue(new Callback<StatusInsert>() {
            @Override
            public void onResponse(Call<StatusInsert> call, Response<StatusInsert> response) {
                StatusInsert statusPost;
                statusPost = response.body();
                if (statusPost.getStatus().equals(1)) {
                    showSnackbar("Berhas");
                    finish();
                    startActivity(getIntent());
                    loading.dismiss();
                } else {
                    String errorMessage = statusPost.getStatus().toString();
                    showSnackbar(errorMessage);
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<StatusInsert> call, Throwable t) {
                showSnackbar("Koneksi Error, silahkan coba lagi");
                t.printStackTrace();
                loading.dismiss();
            }
        });
    }

    private void updateMahasiswa(int id, String nimMhs) {
        showLoading("Updating data...");
        apiInterfaceRest = APIClient.getClientWithApi().create(APIInterfaceRest.class);

        Call<StatusUpdate> call = apiInterfaceRest.updateData(id, nimMhs);
        call.enqueue(new Callback<StatusUpdate>() {
            @Override
            public void onResponse(Call<StatusUpdate> call, Response<StatusUpdate> response) {
                loading.dismiss();

                StatusUpdate statusUpdate;
                statusUpdate = response.body();

                if (statusUpdate.getStatus().equals(1)) {
                    loading.dismiss();
                    showSnackbar("Berhasil update");
                    finish();
                    startActivity(getIntent());
                } else {
                    loading.dismiss();
                    showSnackbar("Gagal update");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onFailure(Call<StatusUpdate> call, Throwable t) {
                loading.dismiss();
                t.printStackTrace();
                showSnackbar("Koneksi error");
            }
        });
    }

    public void showAlert(String kategori) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.form, null);
        editNamaKategori = view.findViewById(R.id.inputNim);
        txtView = view.findViewById(R.id.dialogTitle);

        AlertDialog.Builder builders = new AlertDialog.Builder(MainActivity.this);
        builders.setCancelable(false)
                .setView(view)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        namaKategori = editNamaKategori.getText().toString();
                        if (namaKategori.equals("")) {
                            showSnackbar("Save failed, please fill the field");
                        } else {
                            saveData(namaKategori);
                            showSnackbar(kategori + " has been successfully saved");
                            finish();
                            startActivity(getIntent());
                        }
                    }
                });
        builders.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builders.show();
    }

    private void showDeleteAlert(String namaKtg, int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false)
                .setTitle("Hapus data")
                .setMessage("Hapus " + namaKtg + " dari data?")
                .setPositiveButton("Hapus", (dialog, which) -> {
                    deleteData(id);
                    showSnackbar("Berhasil menghapus " + namaKtg);
                    finish();
                    startActivity(getIntent());

                });
        builder.setNegativeButton("Batal", (dialog, which) -> {
            dialog.cancel();
            showSnackbar("Batal menghapus data");
        });
        builder.show();
    }

    public void showEditAlert(int id, String kategori) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.form, null);
        editNamaKategori = view.findViewById(R.id.inputNim);
        editNamaKategori.setText(kategori);
        txtView = view.findViewById(R.id.dialogTitle);
        txtView.setText("Edit Data");

        AlertDialog.Builder builders = new AlertDialog.Builder(MainActivity.this);
        builders.setCancelable(false)
                .setView(view)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        namaKategori = editNamaKategori.getText().toString();
                        if (namaKategori.equals("")) {
                            showSnackbar("Save failed, please fill the field");
                        } else {
                            updateMahasiswa(id, namaKategori);
                            showSnackbar(namaKategori + " has been successfully saved");
                            finish();
                            startActivity(getIntent());
                        }
                    }
                });
        builders.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builders.show();
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(linearLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void showLoading(String message) {
        loading = new ProgressDialog(MainActivity.this);
        loading.setMessage(message);
        loading.show();
    }

    private void layout(int no, String rowNumber, String nim) {
        tableRow = new TableRow(MainActivity.this);

        if (no % 2 == 0) {
            tableRow.setBackgroundColor(Color.LTGRAY);
        } else {
            tableRow.setBackgroundColor(Color.WHITE);
        }

        viewId = new TextView(MainActivity.this);
        viewId.setText(rowNumber);
        viewId.setPadding(5, 1, 5, 1);
        tableRow.addView(viewId);

        viewNim = new TextView(MainActivity.this);
        viewNim.setText(nim);
        viewNim.setPadding(5, 1, 5, 1);
        tableRow.addView(viewNim);
    }

    private void headerLayout() {
        TableRow tableRow = new TableRow(this);

        headerId.setText("No");
        headerId.setPadding(5, 1, 5, 1);
        headerNim.setText("NIM");
        headerNim.setPadding(5, 1, 5, 1);
        headerNama.setText("Nama");
        headerNama.setPadding(5, 1, 5, 1);
        headerAlamat.setText("Alamat");
        headerAlamat.setPadding(5, 1, 5, 1);
        headerAction.setText("Action");
        headerAction.setPadding(5, 1, 5, 1);

        tableRow.addView(headerId);
        tableRow.addView(headerNim);
        tableRow.addView(headerNama);
        tableRow.addView(headerAlamat);
        tableRow.addView(headerAction);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));

    }
}
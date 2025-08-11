from google.colab import drive
import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPRegressor
from sklearn.metrics import mean_absolute_error, r2_score
import math

drive.mount('/content/gdrive')

PATH = '/content/gdrive/My Drive/Dataset/Cripto'

FILES = {
    'BTC': 'coin_Bitcoin.csv',
    'ETH': 'coin_Ethereum.csv',
    'BNB': 'coin_BinanceCoin.csv',
    'LTC': 'coin_Litecoin.csv',
}


def load_one(path, symbol):
    df = pd.read_csv(path)
    
    low = {c.lower(): c for c in df.columns}
    req = ['date','open','high','low','close']
    for r in req:
        if r not in low:
            raise ValueError(f"Coluna '{r}' não encontrada em {path}. Colunas: {list(df.columns)}")
    out = df[[low['date'], low['high'], low['low'], low['open'], low['close']]].copy()
    out.columns = ['Date','High','Low','Open','Close']
    out['Symbol'] = symbol

    out['Date'] = pd.to_datetime(out['Date'], errors='coerce')
    out[['High','Low','Open','Close']] = out[['High','Low','Open','Close']].apply(pd.to_numeric, errors='coerce')
    out = out.dropna(subset=['Date','High','Low','Open','Close']).sort_values('Date')
    
    out = out[['Symbol','Date','High','Low','Open','Close']]
    return out

long_parts = []
for sym, fname in FILES.items():
    full = os.path.join(PATH, fname)
    part = load_one(full, sym)
    long_parts.append(part)

coins_long = pd.concat(long_parts, ignore_index=True)
coins_long = coins_long.sort_values(['Symbol','Date']).reset_index(drop=True)

OUT_LONG = os.path.join(PATH, 'coins_simple.csv')
coins_long.to_csv(OUT_LONG, index=False)
print('CSV gerado:', OUT_LONG)
print(coins_long.head())


wide = coins_long.pivot(index='Date', columns='Symbol', values='Close').sort_index()

need = ['BTC','ETH','BNB','LTC']
missing = [c for c in need if c not in wide.columns]
if missing:
    raise ValueError('Faltam colunas no pivot: ' + ', '.join(missing))

wide = wide[need].dropna()

X = wide[['ETH','BNB','LTC']]
y = wide['BTC'].shift(-1)
mask = ~y.isna()
X, y = X.loc[mask], y.loc[mask]

split = int(len(X) * 0.8)
X_train, X_test = X.iloc[:split], X.iloc[split:]
y_train, y_test = y.iloc[:split], y.iloc[split:]

scaler = StandardScaler()
X_train_s = scaler.fit_transform(X_train)
X_test_s  = scaler.transform(X_test)

mlp = MLPRegressor(hidden_layer_sizes=(16,8), activation='relu', solver='adam',
                   max_iter=1000, random_state=42)
mlp.fit(X_train_s, y_train)

pred = mlp.predict(X_test_s)

rmse = math.sqrt(((y_test - pred)**2).mean())
mae = mean_absolute_error(y_test, pred)
r2 = r2_score(y_test, pred)
print(f"RMSE: {rmse:.2f}  MAE: {mae:.2f}  R²: {r2:.3f}")

plt.figure(figsize=(12,4))
plt.plot(y_test.index, y_test.values, label='BTC real (t+1)')
plt.plot(y_test.index, pred, label='MLP previsto')
plt.legend()
plt.title('BTC — real vs previsto (test)')
plt.xlabel('Data')
plt.ylabel('Preço de fechamento')
plt.tight_layout()
plt.show()

pred_df = pd.DataFrame({
    'Date': y_test.index,
    'y_true': y_test.values,
    'y_pred_mlp': pred
})
OUT_PRED = os.path.join(PATH, 'predicoes_test.csv')
pred_df.to_csv(OUT_PRED, index=False)
print('Predições salvas em:', OUT_PRED)